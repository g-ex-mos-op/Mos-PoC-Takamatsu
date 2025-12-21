package jp.co.isid.mos.bird.framework.control;

import java.io.File;
import java.io.FilenameFilter;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import jp.co.isid.mos.bird.framework.dao.SessionListenerDao;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * セッション処理。
 * 作成日 : 2005/11/18
 * @author: xnkusama 
 */
public class SessionListenerBean implements HttpSessionBindingListener{
    private static final String SESSION_KEY = "BIRD_SESSION_KEY";

    /** 正常にログインしているユーザＩＤ */
	private java.lang.String _userid = "";
	/** セッションブリッジキー */
    private String _sessionKey= "";
    
    /**
     * 初期化
     * 作成日 : (2001/08/03 16:17:06)
     */
    public SessionListenerBean(String strUser, String sessionKey) {
    	this._userid = strUser;
        this._sessionKey = sessionKey;
    }
    
    class CleanFileFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            boolean bRet = false;
            String acceptPath = BirdContext.getProperty("fileProperty", "imageTempPath");
            bRet = acceptPath.equals(dir.getPath()) && name.startsWith(_userid + "_");
            return bRet;
        }
    }

    /**
     * セッション開始のEvent
     * @param event javax.servlet.http.HttpSessionBindingEvent
     */
    public void valueBound(HttpSessionBindingEvent event) {
    
    }

    /**
     * セッションtimeoutとき処理
     * <BR>
     * @param event javax.servlet.http.HttpSessionBindingEvent
     */
    public void valueUnbound(HttpSessionBindingEvent event) {
    	if (_userid != null && _userid.equals("") == false){
    		try{
    			// 画像テンポラリファイル削除処理
                cleanTemporaryImageFile();
                // セッションブリッジTBL クリア処理
                clearSessionBridgeTable();
    		}catch(Exception e){
    		}
    	}
    }

    // 画像テンポラリファイル削除処理
    private void cleanTemporaryImageFile() {
        String path = BirdContext.getProperty("fileProperty", "imageTempPath");
        
        File tempDir = new File(path);

        File[] targetFiles = tempDir.listFiles(new CleanFileFilter());
        
        if (targetFiles == null) {
            return;
        }
        
        for (int i = 0; i < targetFiles.length; i++) {
            if (targetFiles[i].isFile()) {
                targetFiles[i].delete();
            }
        }
    }
    
    
    /* セッションブリッジTBL クリア処理 */
    private void clearSessionBridgeTable() {
        if (_sessionKey == null || "".equals(_sessionKey.trim())) {
            return;
        }
        
        S2Container container = SingletonS2ContainerFactory.getContainer();
        SessionListenerDao dao = (SessionListenerDao) container.getComponent(SessionListenerDao.class);

        dao.delete(_sessionKey);
    }
}