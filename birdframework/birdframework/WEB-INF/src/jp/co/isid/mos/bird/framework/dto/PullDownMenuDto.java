package jp.co.isid.mos.bird.framework.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlBirdMenu;

/**
 * メニューＤＴＯ
 * @author xytamura
 */
public class PullDownMenuDto  {
    
    /**
     * メニュー検索結果１段目
     */
    private List menu1st;

    /**
     * メニュー検索結果２段目
     */
    private List menu2nd;

    /**
     * 登録系：メニュー検索結果１段目
     */
    private List registMenu1st;

    /**
     * 登録系：メニュー検索結果２段目
     */
    private List registMenu2nd;
    
    /**
     * ヘルプ
     */
    private CtlBirdMenu help;

    /**
     * クリアフラグ
     */
    private boolean clearFlg = true;
    
    /**
     * メニュー表示モード
     *  true:登録系　false:照会系
     */
    private boolean menuDispPtn = false;
    
    /**
     * 登録系メニュー存在フラグ
     */
    private boolean existRegitMenu = false;
    
    /**
     * メニュー表示フラグ
     */
    private boolean menuDispFlg = false;
    
    /**
     * マイメニュー除外メニュー
     */
    private boolean leaveOutMenu;
    
    /**
     * メニュー検索結果を設定します。
     * @param searchResult メニュー検索結果
     */
    public void setMenu1st(final List menu1st) {
        this.menu1st = menu1st;
    }
    
    
    /**
     * メニュー検索結果を取得します。
     * @return メニュー検索結果
     */        
    public List getMenu1st() {
        if(menu1st == null){
            return new ArrayList();
        }
        return menu1st;
    }
    

    /**
     * メニュー検索結果を設定します。
     * @param searchResult メニュー検索結果
     */
    public void setMenu2nd(final List menu2nd) {
        this.menu2nd = menu2nd;
    }
    
    
    /**
     * メニュー検索結果を取得します。
     * @return メニュー検索結果
     */        
    public List getMenu2nd() {
        if(menu2nd == null){
            return new ArrayList();
        }
        return menu2nd;
    }
    
    /**
     * ヘルプメニューをセットします。
     * @param help ヘルプメニュー 
     */
    public void setMenuHelp(final CtlBirdMenu help) {
        this.help = help;
    }
    
    /**
     * ヘルプ名を取得します。
     * @return ヘルプ
     */
    public String getHelpName() {
        if(help == null){
            return "";
        }
        return help.getMenuName();
    }
    
    /**
     * ヘルプのURLを取得します。
     * @return ヘルプのURL
     */
    public String getHelpViewId() {
        if(help == null){
            return "";
        }
        return help.getInitViewId();
    }
    
    /**
     * クリアフラグを取得します。
     * @return クリアフラグ
     */
    public boolean isClearFlg() {
        return clearFlg;
    }
    
    
    /**
     * クリアフラグ
     * @param クリアフラグ
     */
    public void setClearFlg(boolean clearFlg) {
        this.clearFlg = clearFlg;
    }


    public boolean isMenuDispPtn() {
        return menuDispPtn;
    }


    public void setMenuDispPtn(boolean menuDispPtn) {
        this.menuDispPtn = menuDispPtn;
    }


    public List getRegistMenu1st() {
        if (registMenu1st == null) {
            return new ArrayList();
        }
        return registMenu1st;
    }


    public void setRegistMenu1st(List registMenu1st) {
        this.registMenu1st = registMenu1st;
    }


    public List getRegistMenu2nd() {
        if (registMenu2nd == null) {
            return new ArrayList();
        }
        return registMenu2nd;
    }


    public void setRegistMenu2nd(List registMenu2nd) {
        this.registMenu2nd = registMenu2nd;
    }


    public boolean isExistRegitMenu() {
        return existRegitMenu;
    }


    public void setExistRegitMenu(boolean existRegitMenu) {
        this.existRegitMenu = existRegitMenu;
    }


    public boolean isMenuDispFlg() {
        return menuDispFlg;
    }


    public void setMenuDispFlg(boolean menuDispFlg) {
        this.menuDispFlg = menuDispFlg;
    }


    public boolean isLeaveOutMenu() {
        return leaveOutMenu;
    }


    public void setLeaveOutMenu(boolean leaveOutMenu) {
        this.leaveOutMenu = leaveOutMenu;
    }
}
