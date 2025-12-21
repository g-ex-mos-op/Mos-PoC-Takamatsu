package jp.co.isid.mos.bird.config.scheduleregist.dto;


/**
 * 複数ウィンドウ制御用リクエストDTO
 * @author xnkusama
 *
 */
public class ScheduleRegistRequestDto {

    private String sessionKey;

    /* エラーフォーカス制御用 */
    //日付Index
    private int errFocusDateIndex = 0;
    //日付内Index
    private int errFocusIndex = 0;
    //アクティブタブ名称
    private String errFocusTabName;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public int getErrFocusIndex() {
        return errFocusIndex;
    }

    public void setErrFocusIndex(int errFocusIndex) {
        this.errFocusIndex = errFocusIndex;
    }

    public String getErrFocusTabName() {
        return errFocusTabName;
    }

    public void setErrFocusTabName(String errFocusTabName) {
        this.errFocusTabName = errFocusTabName;
    }

    public int getErrFocusDateIndex() {
        return errFocusDateIndex;
    }

    public void setErrFocusDateIndex(int errForcuDateIndex) {
        this.errFocusDateIndex = errForcuDateIndex;
    }
    
}
