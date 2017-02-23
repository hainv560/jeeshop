package net.jeeshop.core.task;

import java.util.concurrent.TimeUnit;

import net.jeeshop.core.front.SystemManager;
import net.jeeshop.core.oscache.ManageCache;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 后台缓存定时更新
 * 
 * @author huangf
 * 
 */
@Component
public class ManageCacheTask implements Runnable {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ManageCacheTask.class);
	@Autowired
	private ManageCache manageCache;

	public void setManageCache(ManageCache manageCache) {
		this.manageCache = manageCache;
	}

	@Override
	public void run() {
		while (true) {
			
			try {
				TimeUnit.SECONDS.sleep(Long.valueOf(SystemManager.getInstance().getProperty("task_SystemAutoNotifyTask_time")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			logger.error("OrderCancelTask.run...");
			try {
				manageCache.loadAllCache();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
