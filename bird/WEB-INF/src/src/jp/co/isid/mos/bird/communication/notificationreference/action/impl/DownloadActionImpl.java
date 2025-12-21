/**
 *
 */
package jp.co.isid.mos.bird.communication.notificationreference.action.impl;

import java.io.IOException;

import jp.co.isid.mos.bird.communication.notificationreference.dto.NotificationReferenceDto;
import jp.co.isid.mos.bird.framework.action.FileDownloadAction;
import jp.co.isid.mos.bird.framework.action.impl.FileDownloadActionImpl;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 *
 * 作成日:2009/06/22
 * @author xkinu
 *
 */
public class DownloadActionImpl extends FileDownloadActionImpl implements FileDownloadAction {
    public static final String download_ACTION_ID = "BCM002A10";
    // 通達文書情報
    private NotificationReferenceDto notificationReferenceDto;

    /**
     * ダウンロード メイン処理
     * @throws IOException
     * @throws ApplicationException
     */
    public void download() throws ApplicationException {
//    	S2Container container = SingletonS2ContainerFactory.getContainer();
//    	HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
//    	getNotificationReferenceDto().setDownloadFileName((String)request.getParameter("downloadFileName"));
    	super.download();
    }

	/**
	 * @return notificationReferenceDto を戻します。
	 */
	public NotificationReferenceDto getNotificationReferenceDto() {
		return notificationReferenceDto;
	}

	/**
	 * @param notificationReferenceDto を クラス変数notificationReferenceDtoへ設定します。
	 */
	public void setNotificationReferenceDto(
			NotificationReferenceDto notificationReferenceDto) {
		this.notificationReferenceDto = notificationReferenceDto;
	}
}
