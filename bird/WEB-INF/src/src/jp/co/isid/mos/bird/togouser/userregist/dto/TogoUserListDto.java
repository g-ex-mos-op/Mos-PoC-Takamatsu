package jp.co.isid.mos.bird.togouser.userregist.dto;

import java.util.List;


public class TogoUserListDto {
    
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * ユーザ名
     */
    private String userNameKj;

    /**
     * 部門コード
     */
    private String bumonCd;
    
    /**
     * 部門名称
     */
    private String bumonName;

    /**
     * STOP_FLG
     */
    private String stopFlg;

    /**
     * 退職日
     */
    private String taishokuDt;
    
    
    /**
     * 選択されたユーザ
     */
    private String selectedUser;
    
    private List userList;

    private boolean initFlg=true;
    
    public String getBumonCd() {
        return bumonCd + bumonName;
    }

    public String getBumonName() {
        return bumonName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserNameKj() {
        return userNameKj;
    }

    public void setBumonCd(String bumonCd) {
        this.bumonCd = bumonCd;
    }

    public void setBumonName(String bumonName) {
        this.bumonName = bumonName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }

    public String getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(String selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List getUserList() {
        return userList;
    }

    public void setUserList(List userList) {
        this.userList = userList;
    }

    public boolean isInitFlg() {
        return initFlg;
    }

    public void setInitFlg(boolean initFlg) {
        this.initFlg = initFlg;
    }

    public String getStopFlg() {
        return stopFlg;
    }

    public void setStopFlg(String stopFlg) {
        this.stopFlg = stopFlg;
    }

    public String getTaishokuDt() {
        return taishokuDt;
    }

    public void setTaishokuDt(String taishokuDt) {
        this.taishokuDt = taishokuDt;
    }
    
    //その時点でDTOに入っているデータをクリアする。
    public void clearInputs() {
        this.userId = "";
        this.userNameKj = "";
        this.bumonCd = "";
        this.bumonName = "";
        this.stopFlg = "";
        this.taishokuDt = "";
        this.selectedUser = "";
        this.userList = null;
        this.initFlg = true;
    }


    
}
