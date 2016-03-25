package zhexian.learn.cnblogs.image;

import android.graphics.Bitmap;

import zhexian.learn.cnblogs.util.Utils;


/**
 * 图片缓存类，用于将图片Bitmap放到内存中
 */
public class MemoryCacheTask extends BaseImageAsyncTask {

    private String httpPath;
    private String LocalPath;

    public MemoryCacheTask(String httpPath, String localPath) {
        this.httpPath = httpPath;
        this.LocalPath = localPath;
    }

    @Override
    public int getTaskId() {
        return CACHE_IMAGE_TASK_ID;
    }

    @Override
    public String getUrl() {
        return httpPath;
    }

    @Override
    public void run() {
        Bitmap bitmap = ZImage.ready().getFromMemoryCache(httpPath);

        if (bitmap == null) {
            bitmap = Utils.getBitMap(LocalPath);

            if (bitmap != null && bitmap.getByteCount() > 0)
                ZImage.ready().putToMemoryCache(httpPath, bitmap);
        }
        ImageTaskManager.getInstance().Done(getTaskId());
    }
}
