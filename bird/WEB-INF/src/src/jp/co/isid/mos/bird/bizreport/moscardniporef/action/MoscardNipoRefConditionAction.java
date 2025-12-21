package jp.co.isid.mos.bird.bizreport.moscardniporef.action;

/**
 * 営業日報の条件部画面アクション・インターフェース
 * 
 * @author   xkhata
 * @modifier xjung  2006/10/03 総合営業日報タブ連携対応
 */
public interface MoscardNipoRefConditionAction {
    /**
     * アクション【初期化処理】
     * @return String 遷移先ビューID
     */   
    public String initialize();
    /**
     * アクション【SV検索ボタン】処理
     * @return String 遷移先ビューID
     */   
    public String callSvForm();
    /**
     * アクション【実行】処理
     * @return String 遷移先ビューID
     */   
    public String search();

}