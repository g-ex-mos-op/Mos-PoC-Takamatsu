/*
 * 作成日: 2008/11/19
 *
 */
package jp.co.isid.mos.bird.framework.logic;


/**
 * マイメニュー登録可能画面判断ロジック
 * 
 * @author xkinu
 */
public interface GetLeaveOutMenuLogic {
    /**
     * マイメニュー登録可能なメニューかを判定する。
     * 
     * @param pgmId  機能ＩＤ
     * @return マイメニュー登録可能判断値(true:可能、false:不可)
     */
    public boolean isLeaveOutMenu(final String pgmId);
}