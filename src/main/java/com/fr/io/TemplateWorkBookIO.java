package com.fr.io;

import com.fr.base.Env;
import com.fr.base.io.XMLEncryptUtils;
import com.fr.general.FRLogManager;
import com.fr.general.Inter;
import com.fr.general.ModuleContext;
import com.fr.main.TemplateWorkBook;
import com.fr.main.impl.WorkBook;
import com.fr.report.module.EngineModule;
import com.fr.stable.StableUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

public final class TemplateWorkBookIO {
	public static TemplateWorkBook readTemplateWorkBook(Env paramEnv, String paramString) throws Exception {
		if (paramString == null) {
			return null;
		}
		WorkBook localWorkBook = null;
		FRLogManager.declareResourceReadStart(paramString);
		ModuleContext.startModule(EngineModule.class.getName());
		try {
			InputStream localInputStream = paramEnv.readBean(paramString, "reportlets");
			if (localInputStream != null) {
				if (paramString.endsWith(".cpt")) {
					localWorkBook = new WorkBook();
					localWorkBook.readStream(XMLEncryptUtils.decodeInputStream(localInputStream));
				} else {
					Class localClass;
					if (paramString.endsWith(".xls")) {
						localClass = Class.forName("com.fr.io.importer.ExcelReportImporter");
						localWorkBook = (WorkBook) localClass
								.getMethod("generateWorkBookByStream", new Class[] { InputStream.class })
								.invoke(localClass.newInstance(), new Object[] { localInputStream });
					} else if (paramString.endsWith(".xlsx")) {
						localClass = Class.forName("com.fr.io.importer.Excel2007ReportImporter");
						localWorkBook = (WorkBook) localClass
								.getMethod("generateWorkBookByStream", new Class[] { InputStream.class })
								.invoke(localClass.newInstance(), new Object[] { localInputStream });
					}
				}
				localInputStream.close();
			} else {
				throw new FileNotFoundException(Inter.getLocText("NS_exception_noTemplate") + ":" + paramString);
			}
		} catch (Exception localException) {
			throw FRLogManager.createLogPackedException(localException);
		} finally {
			FRLogManager.declareResourceReadEnd();
		}
		return localWorkBook;
	}

	public static boolean writeTemplateWorkBook(Env paramEnv, TemplateWorkBook paramTemplateWorkBook,
			String paramString) throws Exception {
		FRLogManager.declareResourceWriteStart(paramString);
		ModuleContext.startModule(EngineModule.class.getName());
		try {
			OutputStream localOutputStream = paramEnv.writeBean(paramString, "reportlets");
			((WorkBook) paramTemplateWorkBook).export(localOutputStream);
			localOutputStream.flush();
			localOutputStream.close();
		} catch (Exception localException) {
			throw FRLogManager.createLogPackedException(localException);
		} finally {
			FRLogManager.declareResourceWriteEnd();
		}
		return true;
	}

	public static long getLastModifiedTime(Env paramEnv, String paramString) {
		try {
			File localFile = new File(
					StableUtils.pathJoin(new String[] { paramEnv.getPath(), "reportlets", paramString }));
			return localFile.lastModified();
		} catch (Exception localException) {
		}
		return 0L;
	}
}
