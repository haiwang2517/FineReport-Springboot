package com.fr.web.cache;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.fr.base.Env;
import com.fr.base.FRContext;
import com.fr.dav.LocalEnv;
import com.fr.file.CacheManager;
import com.fr.general.GeneralContext;
import com.fr.io.TemplateWorkBookIO;
import com.fr.main.TemplateWorkBook;

public class ReportEntry implements Serializable {

	private Logger logger = LoggerFactory.getLogger(ReportEntry.class);

	public static final int MAX_CACHE_SIZE = 1000;
	private String bookPath;
	private long lastmodifiedtime;
	private final Map reportCacheMap = new HashMap(16);
	private transient WeakReference reference;

	public ReportEntry(String paramString) {
		this.bookPath = paramString;
	}

	public TemplateWorkBook getWorkBookTemplate() {
		logger.info("进入getWorkBookTemplate()");
		TemplateWorkBook localTemplateWorkBook = null;
		synchronized (this) {
			long l = 0L;
			if ((CacheManager.getProviderInstance().isAlwaysReloadTpl())
					|| ((l = TemplateWorkBookIO.getLastModifiedTime(FRContext.getCurrentEnv(),
							this.bookPath)) != this.lastmodifiedtime)
					|| (this.reference == null)
					|| ((localTemplateWorkBook = (TemplateWorkBook) this.reference.get()) == null)) {

				logger.info("FRContext.getCurrentEnv().getPath():[{}]", FRContext.getCurrentEnv().getPath());
				logger.info("bookPath:[{}]", this.bookPath);
				logger.info("lastmodifiedtime:[{}]", this.lastmodifiedtime);
				logger.info("reference:[{}]", this.reference);
				try {
					localTemplateWorkBook = TemplateWorkBookIO.readTemplateWorkBook(FRContext.getCurrentEnv(),
							this.bookPath);
				} catch (Exception localException) {
					logger.error("异常ReportEntry.getWorkBookTemplate()", localException);
					FRContext.getLogger().error(localException.getMessage(), localException);
					throw new RuntimeException(localException.getMessage());
				}
				if (localTemplateWorkBook != null) {
					this.reference = new WeakReference(localTemplateWorkBook);

					logger.info("不为空重新赋值localTemplateWorkBook:[{}]", this.reference);

					this.lastmodifiedtime = l;
				}
			}
		}
		return localTemplateWorkBook;
	}

	public ReportCache getReportCache(Map paramMap, long paramLong, boolean paramBoolean) {
		Object localObject1 = null;
		synchronized (this.reportCacheMap) {
			long l1 = System.currentTimeMillis();
			Iterator localIterator = this.reportCacheMap.values().iterator();
			while (localIterator.hasNext()) {
				ReportCache localReportCache = (ReportCache) localIterator.next();
				long l2 = localReportCache.createTime();
				if (l2 < 0L) {
					if (localReportCache.accept(paramMap)) {
						localObject1 = localReportCache;
						break;
					}
				} else if (localReportCache.accept(paramMap)) {
					if (isCacheAlive(paramBoolean, paramLong, l1, l2)) {
						localObject1 = localReportCache;
						break;
					}
					localIterator.remove();
					break;
				}
			}
			if (localObject1 == null) {
				localObject1 = new ReportCache(this, paramMap);
				if (this.reportCacheMap.size() > 1000) {
					this.reportCacheMap.clear();
				}
				this.reportCacheMap.put(((ReportCache) localObject1).getId(), localObject1);
			}
		}
		return (ReportCache) localObject1;
	}

	private boolean isCacheAlive(boolean paramBoolean, long paramLong1, long paramLong2, long paramLong3) {
		return (paramBoolean) || ((paramLong1 != 0L) && ((paramLong1 < 0L) || (paramLong2 - paramLong3 < paramLong1)));
	}

	public String getBookPath() {
		return this.bookPath;
	}
}
