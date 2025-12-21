package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.common.entity.MstKanriKikan;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenMstRegistDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity.MstKanriMenuGroup;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity.MstShanaiMenu;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity.UIKanriMenu;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuVerifier;

/**
 * モスチキン管理マスタ登録
 * static 処理保持クラス
 * 
 * @author xkinu
 *
 */
public class MosChickenMstRegistUtil {
    /* 画面ID */
    public static final String SCREEN_ID = "BBS015";
    /* VIEWID：初期(条件)画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";
    /* VIEWID：編集画面 */
    public static final String VIEW_ID_EDIT = SCREEN_ID+"V02";
    /* VIEWID：メニュー追加画面 */
    public static final String VIEW_ID_ADDMENU = SCREEN_ID+"V03";
    /* VIEWID：食包材選択画面 */
    public static final String VIEW_ID_CHOISESHOKU = SCREEN_ID+"V04";
    /* VIEWID：確認or参照画面 */
    public static final String VIEW_ID_CONFIRM = SCREEN_ID+"V05";
    /**
     * 新規登録判断フラグ値：参照処理時
     */
    public static final String MODETYPE_VIEW = "VIEW";
    /**
     * 新規登録判断フラグ値：新規登録処理時
     */
    public static final String MODETYPE_INSERT = "INSERT";
    /**
     * 新規登録判断フラグ値：更新登録処理時
     */
    public static final String MODETYPE_UPDATE = "UPDATE";
    /**
     * リターン値キー：[[管理対象メニュー]]
     */
    public static final String RPK_DUPLICATE_EX = "duplicateEx";
    /**
     * リターン値キー：重複メニューグループコード
     */
    public static final String RPK_DUPLICATE_MGROUPCD = "duplicateMGroup";
    /**
     * リターン値キー：重複メニューコード
     */
    public static final String RPK_DUPLICATE_MENUCD = "duplicateMenu";
    /**
     * リターン値キー：[[管理対象メニュー]]
     */
    public static final String RPK_KANRI_MENU= "listKanriMenu";
    /**
     * リターン値キー：メニュー追加開始インデックス
     */
    public static final String RPK_MENUSTARTINDEX= "menuStartIndex";
    
    private MosChickenMstRegistUtil() {
        super();
    }
    /**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    /**
     * 管理対象期間リスト生成取得処理
     * 
     * @param dto
     */
    public static void createKikan(MosChickenMstRegistDto dto) {
        //新規用管理対象期間リスト設定
        dto.setListKanriKikan(createKikan());
    }
    /**
     * 管理対象期間リスト生成取得処理
     * 
     * @param targetMenuGroup
     * @param listMenuGroup
     * @param listMenu
     */
    public static List createKikan() {
        //新規用管理対象期間リスト設定
        List listKikan = new ArrayList();
        MstKanriKikan entity = new MstKanriKikan();
        entity.setYobiFrom("");
        entity.setYobiTo("");
        listKikan.add(entity);
        return listKikan;
    }
    /**
     * メニューグループ追加処理
     * 
     * @param targetMenuGroup
     * @param listMenuGroup
     * @param listMenu
     */
    public static void addMenuGroup(MosChickenMstRegistDto dto) {
        int newMenuGroupCounter = dto.getNewMenuGroupCounter();
        //[[管理対象メニューグループ]]
        List listMenuGroup = dto.getListKanriMenuGroup();
        if(listMenuGroup == null || listMenuGroup.size() < 1){
            newMenuGroupCounter = 0;
        }
        else if(listMenuGroup.size() == 10) {
            //既に１０個メニューグループがある場合はエラーを発生させる。
            //MSG【E0404】{1}為、{2}できませんん。
            CannotExecuteWithReasonException exCannotExecute = 
                new CannotExecuteWithReasonException("商品グループは既に10個設定されている", "商品グループを追加");
            exCannotExecute.setHtmlElementIndex(0);
            exCannotExecute.setHtmlElementName("addMenuGroupBt");
            throw exCannotExecute;
        }
        newMenuGroupCounter++;
        //１．新規用モスチキン管理対象メニューグループエンティティをメニューグループリストへ追加。
        listMenuGroup = addMenuGroup(listMenuGroup, String.valueOf(newMenuGroupCounter));
        //２．処理１の結果をDTOへ設定する。
        dto.setListKanriMenuGroup(listMenuGroup);
        //処理対象メニューコードインデックス(最終行のインデックス）を設定する。
        int targetIndex = dto.getListKanriMenuGroup().size()-1;
        dto.setTargetMGroupIndex(String.valueOf(targetIndex));
        //新規メニューグループカウンターをカウントアップし、その値をDTOへ設定する。
        dto.setNewMenuGroupCounter(newMenuGroupCounter);
    }
    /**
     * メニューグループ追加処理
     * 
     * @param listMenuGroup
     * @param newMenuGroup　追加対象メニューグループコード
     * @return
     */
    public static List addMenuGroup(List listMenuGroup, String newMenuGroup) {
        if(listMenuGroup == null){
            listMenuGroup = new ArrayList();
        }
        MstKanriMenuGroup entity = new MstKanriMenuGroup();
        CodeFormatter cdf = new CodeFormatter(2, "00");
        cdf.setFormatPattern("00");
        newMenuGroup =  cdf.format(newMenuGroup, true);
        entity.setMenuGroup("INSERT"+newMenuGroup);
        
        //追加対象メニューグループコードの行を追加する。
        listMenuGroup.add(listMenuGroup.size(), entity);
        return listMenuGroup;
    }
    /**
     * メニューグループ削除処理
     * 
     * [[管理対象メニューグループ]]と[[管理対象メニュー]]の２つのリストから
     * 処理対象メニューグループコードの行を削除します。
     * 
     * @param targetMenuGroup
     * @param listMenuGroup
     * @param listMenu
     */
    public static List removeMenuGroup(String targetMenuGroup, List listMenuGroup){
        if(targetMenuGroup == null || listMenuGroup == null){
            return listMenuGroup;
        }
        for(int i=0; i<listMenuGroup.size(); i++){
            MstKanriMenuGroup entity = (MstKanriMenuGroup)listMenuGroup.get(i);
            if(targetMenuGroup.equals(entity.getMenuGroup())){
                //削除対象メニューグループコードの行を削除する。
                listMenuGroup.remove(i);
                break;
            }
        }
        return listMenuGroup;
    }
    /**
     * メニューグループ削除処理
     * 
     * 引数のDTOの[[管理対象メニューグループ]]と[[管理対象メニュー]]の２つのリストから
     * 処理対象メニューグループコードの行を削除します。
     * @param dto MosChickenMstRegistDto
     */
    public static void removeMenuGroup(MosChickenMstRegistDto dto){
        //１．ユーティル【static 処理保持クラス】.メニューグループ削除処理を実行する。
        String targetMenuGroup=dto.getTargetMenuGroupCd();
        //[[管理対象メニューグループ]]
        List listMenuGroup = dto.getListKanriMenuGroup();
        //[[管理対象メニュー]]
        List listMenu = dto.getListKanriMenu();
        listMenuGroup = removeMenuGroup(targetMenuGroup, listMenuGroup);
        listMenu = removeMenu(targetMenuGroup, listMenu);
        //２．結果をDTOへ設定する。
        dto.setListKanriMenuGroup(listMenuGroup);
        dto.setListKanriMenu(listMenu);
        //３．[[管理対象メニューグループ]]の件数が０件の場合、下記の処理を行う。
        if(listMenuGroup == null || listMenuGroup.size() < 1){
            //新規用管理対象メニューグループ設定
            //３－１．新規用モスチキン管理対象メニューグループエンティティをメニューグループリストへ追加。
           MosChickenMstRegistUtil.addMenuGroup(dto);
        }
        
    }
    /**
     * メニュー件数チェック処理
     * 
     * １．DTO.メニューグループコードを取得する。
     * ２．DTO.[[管理対象メニュー]]を取得する。
     * ３．ユーティル【static 処理保持クラス】.指定メニューグループコードのメニュー件数取得 を実行する。
     * ４．件数が30以上の場合はMSG【E0404】{1}為、{2}できません。のExceptionを発生させる。
     * 
     * @param dto MosChickenMstRegistDto
     */
    public static void checkMenuCnt(MosChickenMstRegistDto dto){
        //１．DTO.メニューグループコードを取得する。
        String targetMenuGroup=dto.getTargetMenuGroupCd();
        //２．DTO.[[管理対象メニュー]]
        List listMenu = dto.getListKanriMenu();
        //３．ユーティル【static 処理保持クラス】.指定メニューグループコードのメニュー件数取得 を実行する。
        int cnt = cntMenu(targetMenuGroup, listMenu);
        if(cnt >= 30){
            //指定商品グループに既に30個メニューがある場合はエラーを発生させる。
            //MSG【E0404】{1}為、{2}できません。
            CannotExecuteWithReasonException exCannotExecute = 
                new CannotExecuteWithReasonException("対象の商品グループ内に既にメニューが30個設定されている", "メニューを追加");
            exCannotExecute.setHtmlElementIndex(getTargetMGroupIndex(targetMenuGroup, dto.getListKanriMenuGroup()));
            exCannotExecute.setHtmlElementName("addMenuBt");
            throw exCannotExecute;
        }
    }
    /**
     * 選択メニュー設定可能判断処理
     * 
     * 設定可能メニュー件数よりも設定希望メニュー件数が多い場合はエラーを発生されます。
     * 
     * @param targetMenuGroup
     * @param listShanaiMenu
     * @param listMenu
     */
    public static void checkMenuCnt(String targetMenuGroup, List listShanaiMenu, List listMenu){
        //ユーティル【static 処理保持クラス】.設定済みメニュー件数取得処理を実行する。
        int setedMenuCnt = cntMenu(targetMenuGroup, listMenu);
        //設定可能メニュー件数
        int setCnt = 30-setedMenuCnt;
        //設定希望メニュー件数
        int settingCnt =0;
        for(int i=0; i<listShanaiMenu.size(); i++){
            //４－０.スタート！！
            MstShanaiMenu selectData = (MstShanaiMenu)listShanaiMenu.get(i);
            if(!selectData.isSelectFlg()){
                //処理４－０へ。
                continue;
            }
            String targetMenuCd = selectData.getMenuCd();
            if(checkExisMenuCd(targetMenuGroup, targetMenuCd, listMenu)){
                continue;
            }
            settingCnt++;
        }
        if(settingCnt > setCnt){
            //設定希望メニュー件数 > 設定可能メニュー件数の場合はエラーを出力する。
            //指定商品グループに既に30個メニューがある場合はエラーを発生させる。
            //MSG【E0404】{1}為、{2}できません。
            CannotExecuteWithReasonException exCannotExecute = 
                new CannotExecuteWithReasonException("対象の商品グループ内に既にメニューが既に"+setedMenuCnt+"個設定されている", (setCnt+1)+"個以上のメニューは追加");
            throw exCannotExecute;
        }
    }
    /**
     * 指定メニューグループコードのメニュー件数取得メソッド
     * 
     * @param ckanriNo
     * @param targetMenuGroup
     * @param listTargetMenu
     * @return　int 件数
     */
    public static int cntMenu(String targetMenuGroup, List listTargetMenu){
        int cnt = 0;
        if(targetMenuGroup == null || listTargetMenu == null){
            return cnt;
        }
        for(int i=0; i<listTargetMenu.size(); i++){
            UIKanriMenu entity = (UIKanriMenu)listTargetMenu.get(i);
            if(targetMenuGroup.equals(entity.getMenuGroup())){
                cnt++;
            }
        }
        return cnt;
    }
    /**
     * メニュー追加処理
     * 
     * １．事前条件判断処理 パラメーター.[[追加対象メニュー]]がnullではない。nullの場合はnullリターン。
     * ２．パラメーター.[[管理対象メニュー]]がnullの場合はインスタンス化する。
     * ３．リターン値Mapをインスタンス化する。
     * ４．パラメーター.[[追加対象メニュー]]の件数分下記の処理を行う。
     * 　　４－１．ユーティル【static 処理保持クラス】.メニュー重複チェックを行う
     *             パラメーター： パラメーター.メニューグループコード
     *                            [追加対象メニュー].メニューコード
     *                            [追加対象メニュー].メニュー名称
     *                            パラメーター.[[管理対象メニュー]]
     *     ４－２．処理４－１で重複Exceptionが発生した場合は下記の処理を行う。
     *     　　a.リターン値Mapにすでに重複Exceptionが格納されている場合は、処理４－１へ。
     *     　　b.リターン値Mapに処理４－１で重複Exceptionを"duplicateEx"をキーに格納する。処理４－１へ。
     *     ４－３．[new管理対象メニュー]をインスタンス化し、下記の値を設定する。
     *      　　　　　　　[new管理対象メニュー].管理番号               = パラメーター.管理番号
     *      　　　　　　　[new管理対象メニュー].メニューグループコード = パラメーター.メニューグループコード
     *      　　　　　　　[new管理対象メニュー].メニューコード         = [追加対象メニュー].メニューコード
     *      　　　　　　　[new管理対象メニュー].メニュー名称           = [追加対象メニュー].メニュー名称
     *      　　　　　　　[new管理対象メニュー].食包材コード           = ''
     *      　　　　　　　[new管理対象メニュー].換算値                 = 0
     *      　　　　　　　[new管理対象メニュー].集計グループ           = 'A'
     *      ４－４．パラメーター.[[管理対象メニュー]]のパラメーター.メニューグループコードの
     *      　　　　データの最終行の次に[new管理対象メニュー]をaddする。
     * ５．パラメーター.[[管理対象メニュー]]をリターン値Mapへ格納する。
     * ６．リターン値Mapをリターンする。
     * 
     * @param ckanriNo          管理番号
     * @param targetMenuGroup メニューグループコード
     * @param listSearchData　　[[追加対象メニュー]]
     * @param listTargetMenu          [[管理対象メニュー]]
     * @return
     */
    public static Map addMenu(String ckanriNo, String targetMenuGroup, List listSearchData, List listTargetMenu) {
        //１．事前条件判断処理
        if(listSearchData == null){
            return null;
        }
        //２．パラメーター.[[管理対象メニュー]]がnullの場合はインスタンス化する。
        if(listTargetMenu == null){
            listTargetMenu = new ArrayList();
        }
        //リターン値Mapをインスタンス化する。
        Map params = new HashMap();
        String lastMenuGroup = "";
        //４．パラメーター.[[追加対象メニュー]]の件数分下記の処理を行う。
        int menuIndex=0;
        for(int i=0; i<listSearchData.size(); i++){
            //４－０.スタート！！
            MstShanaiMenu selectData = (MstShanaiMenu)listSearchData.get(i);
            if(!selectData.isSelectFlg()){
                //処理４－０へ。
                continue;
            }
            //追加対象メニューコード
            String insertCd = selectData.getMenuCd();
            String insertName = selectData.getMenuNameKj();
            try{
                //４－１．ユーティル【static 処理保持クラス】.メニュー重複チェックを行う
                checkDuplicateMenu(targetMenuGroup, insertCd, insertName, listTargetMenu);
            }catch (GenericMessageException duplicateEx){
                //４－２．処理４－１で重複Exceptionが発生した場合は下記の処理を行う。
                if(!params.containsKey(RPK_DUPLICATE_EX)){
                    //b.リターン値Mapに処理４－１で重複Exceptionを"duplicateEx"をキーに格納する。
                    params.put(RPK_DUPLICATE_EX, duplicateEx);
                    params.put(RPK_DUPLICATE_MGROUPCD, targetMenuGroup);
                    params.put(RPK_DUPLICATE_MENUCD, insertCd);
                }
                //処理４－０へ。
                continue;
            }
            
            //４－３．[new管理対象メニュー]をインスタンス化し、下記の値を設定する。
            UIKanriMenu newEntity = new UIKanriMenu();
            newEntity.setCkanriNo(ckanriNo);
            newEntity.setMenuGroup(targetMenuGroup);
            newEntity.setMenuCd(insertCd);
            newEntity.setMenuNameKj(insertName);
            newEntity.setShokuCd("");
            newEntity.setShokuNameKna("");
            newEntity.setConvValue(new BigDecimal("0"));
            newEntity.setSumGroup("A");
            
            //４－４．パラメーター.[[管理対象メニュー]]の行に[new管理対象メニュー]をaddする。
            if(listTargetMenu.size() == 0){
                //４－４－１．１件もメニューが設定されていない場合は、下記の処理を実行する。
                //a.パラメーター.[[管理対象メニュー]]の先頭行へ[new管理対象メニュー]をaddする。
                listTargetMenu.add(0, newEntity);//追加します。
                //b.リターン値Mapに追加メニュー開始インデックスを格納する。
                params.put(RPK_MENUSTARTINDEX, "0");
                //前行メニューグループコード変数へメニューグループコードを設定する。
                lastMenuGroup = newEntity.getMenuGroup();
            }else{
                //４－４－２．１件以上メニューが設定されている場合は、下記の処理を実行する。
                for(int m=menuIndex; m<listTargetMenu.size(); m++){
                    menuIndex = m;
                    //a.先頭から順番にパラメーター.[[管理対象メニュー]].[管理対象メニュー]を取得する。
                    UIKanriMenu entityMenu = (UIKanriMenu)listTargetMenu.get(m);
                    if(targetMenuGroup.equals(lastMenuGroup)){
                        //b.パラメーター.メニューグループコードと前行.メニューグループコードが同じ場合
                        //  下記の処理を行う。
                        if(!targetMenuGroup.equals(entityMenu.getMenuGroup())){
                            //b-1.パラメーター.メニューグループコードと現行行のメニューグループコードが違う場合
                            //　　下記の処理を行う。
                            //1).現在のインデックスの位置に[new管理対象メニュー]を追加する。
                            listTargetMenu.add(m, newEntity);
                            if(!params.containsKey(RPK_MENUSTARTINDEX)){
                                //2).まだ追加メニュー開始インデックスが設定されていない場合は
                                //   リターン値Mapに追加メニュー開始インデックスを格納する。
                                params.put(RPK_MENUSTARTINDEX, String.valueOf(m));
                            }
                            break;
                        }
                    }                    
                    lastMenuGroup = entityMenu.getMenuGroup();
                    if(m == (listTargetMenu.size()-1)){
                        //c.現行行が最終行の場合下記の処理を行う。
                        int addIndex = m+1;
                        //1).現在のインデックスよりも一つ後の位置に[new管理対象メニュー]を追加する。
                        listTargetMenu.add(addIndex, newEntity);//追加します。
                        if(!params.containsKey(RPK_MENUSTARTINDEX)){
                            //2).まだ追加メニュー開始インデックスが設定されていない場合は
                            //   リターン値Mapに追加メニュー開始インデックスを格納する。
                            params.put(RPK_MENUSTARTINDEX, String.valueOf(addIndex));
                        }
                        break;
                    }
                }
            }
        }
        //５．パラメーター.[[管理対象メニュー]]をリターン値Mapへ格納する。
        params.put(RPK_KANRI_MENU, listTargetMenu);
        //６．エラー対象インデックスを設定。
        /* 処理４－１－bの時点ではインデックスが変わる可能性があるので、
         * 追加可能な全てのメニューを設定後、
         * 重複コードで検索し、インデックスを取得する必要がある。
         */
        putDuplicateParamIndex(params, listTargetMenu);
        //７．リターン値Mapをリターンする。
        return params;
    }
    /**
     * メニュー重複チェック
     * 
     * @param targetMenuGroup　処理対象メニューグループコード
     * @param targetMenuCd     処理対象メニューコード
     * @param targetMenuName   処理対象メニュー名称
     * @param listTargetMenu
     */
    public static void checkDuplicateMenu(String targetMenuGroup, String targetMenuCd, String targetMenuName, List listTargetMenu) {
        if(targetMenuGroup == null || targetMenuCd == null || listTargetMenu == null){
            return;
        }
        for(int i=0; i<listTargetMenu.size(); i++){
            UIKanriMenu entity = (UIKanriMenu)listTargetMenu.get(i);
            if(targetMenuGroup.equals(entity.getMenuGroup()) 
                    && targetMenuCd.equals(entity.getMenuCd())){
                //MSG【E0204】指定したメッセージのみ出力されます。
                throw new GenericMessageException("同一のメニューが既に登録されています。(" +
                "メニュー："+targetMenuCd+" "+targetMenuName+" )", "menuCd", 0);
            }
        }
        
    }
    /**
     * 重複Exception対象インデックス設定処理
     * 
     * @param params
     * @param targetList
     */
    public static void putDuplicateParamIndex(Map params, List targetList){
        if(params == null || targetList == null){
            return;
        }
        GenericMessageException dEx = (GenericMessageException)params.get(RPK_DUPLICATE_EX);
        if(dEx == null){
            return;
        }
        String dMGroup = (String)params.get(RPK_DUPLICATE_MGROUPCD);
        String dMenuCd = (String)params.get(RPK_DUPLICATE_MENUCD);
        for(int i=0; i<targetList.size(); i++){
            if(targetList.get(i) instanceof UIKanriMenu){
                UIKanriMenu entity = (UIKanriMenu)targetList.get(i);
                if(dMGroup.equals(entity.getMenuGroup()) && dMenuCd.equals(entity.getMenuCd())){
                    dEx.setHtmlElementIndex(i);
                    params.put(RPK_DUPLICATE_EX, dEx);
                    break;
                }
            }
            else if(targetList.get(i) instanceof MstKanriMenuGroup){
                MstKanriMenuGroup entity = (MstKanriMenuGroup)targetList.get(i);
                if(dMGroup.equals(entity.getMenuGroup())){
                    dEx.setHtmlElementIndex(i);
                    params.put(RPK_DUPLICATE_EX, dEx);
                    break;
                }
            }
        }
        
    }
    /**
     * 食包材設定処理
     * 
     * １．事前条件判断処理 
     * 　　　パラメーター.[[追加対象メニュー]]がnullか、
     * 　　　またはパラメーター.[[管理対象メニュー]]がnullの場合はnullリターン。
     * ２．リターン値Mapをインスタンス化する。
     * ３．パラメーター.[[追加対象メニュー]]の件数分下記の処理を行う。
     * 　　３－０.スタート！！
     * 　　３－１．選択対象外の場合は処理３－０へ戻る。
     * 　　３－２．ユーティル【static 処理保持クラス】.食包材重複チェックを行う
     *             パラメーター： パラメーター.メニューグループコード
     *                            パラメーター.[[追加対象メニュー]]
     *                            パラメーター.[[管理対象メニュー]]
     *                            パラメーター.メニューコード
     *                            パラメーター.メニュー名称
     *                            
     *     ３－３．処理３－２で重複Exceptionが発生した場合は下記の処理を行う。
     *     　　a.リターン値Mapにすでに重複Exceptionが格納されている場合は、処理３－０へ。
     *     　　b.リターン値Mapに処理４－１で重複Exceptionを"duplicateEx"をキーに格納する。処理３－０へ。
     *     
     *     ３－４．以下の条件で、パラメーター.[[管理対象メニュー]]で対象のデータ行へ対象食包材コードと名称を[管理対象メニュー]へ設定する。
     *      　　　b.パラメーター.メニューグループコードと現行行.メニューグループコードが同じ場合
     *            　かつパラメーター.メニューコードと現行行.メニューコードが同じ場合は下記の処理を行う。
     *            1)対象食包材コードと名称を[管理対象メニュー]へ設定する。
     *      　　　　　　　[管理対象メニュー].食包材コード           = [追加対象メニュー].食包材コード
     *      　　　　　　　[管理対象メニュー].食包材名称             = [追加対象メニュー].食包材名称
     *            2).現在のインデックスの位置に[管理対象メニュー]を上書きする。
     *            3).まだ追加メニュー開始インデックスが設定されていない場合は
     *               リターン値Mapに追加メニュー開始インデックスを格納する。
     *               
     * ４．パラメーター.[[管理対象メニュー]]をリターン値Mapへ格納する。
     *  
     * ５．エラー対象インデックスを設定。
     *     重複エラーした一番先頭のインデックスを設定します。
     * 
     * @param params          処理データ値Map
     * @param ckanriNo          管理番号
     * @param targetMenuGroup メニューグループコード
     * @param targetShokuCd　　 選択対象食包材コード
     * @param targetShokuName　 選択対象食包材名称
     * @param listTargetMenu    [[管理対象メニュー]]
     * @param targetMenuCd 　　メニューコード
     * @param targetMenuName 　メニュー名称
     * @return
     */
    public static void updateShokuhouzai(Map params, String ckanriNo, String targetMenuGroup
            , String targetShokuCd, String targetShokuName, List listTargetMenu, String targetMenuCd) {
        //１．事前条件判断処理
        if(targetShokuCd == null || listTargetMenu == null){
            //パラメーター.[[追加対象メニュー]]がnullか、またはパラメーター.[[管理対象メニュー]]がnullの場合は
            //nullをリターンする。
            return;
        }
        try{
            //２．ユーティル【static 処理保持クラス】.食包材重複チェックを行う
            checkDuplicateShokuhouzai(targetMenuGroup, targetMenuCd, targetShokuCd, listTargetMenu);
            //３－４．対象行へ対象食包材コードと名称を[管理対象メニュー]へ設定する。
            for(int m=0; m<listTargetMenu.size(); m++){
                UIKanriMenu entityMenu = (UIKanriMenu)listTargetMenu.get(m);
                if(targetMenuGroup.equals(entityMenu.getMenuGroup())
                        && targetMenuCd.equals(entityMenu.getMenuCd())){
                    //条件.パラメーター.メニューグループコードと現行行.メニューグループコードが同じ場合
                    //  かつパラメーター.メニューコードと現行行.メニューコードが同じ場合は下記の処理を行う。
                    //1)対象食包材コードと名称を[管理対象メニュー]へ設定する。
                    entityMenu.setShokuCd(targetShokuCd);
                    entityMenu.setShokuNameKna(targetShokuName);
                    //2).現在のインデックスの位置に[管理対象メニュー]を上書きする。
                    listTargetMenu.set(m, entityMenu);
                    if(!params.containsKey(RPK_MENUSTARTINDEX)){
                        //3).まだ追加メニュー開始インデックスが設定されていない場合は
                        //   リターン値Mapに追加メニュー開始インデックスを格納する。
                        params.put(RPK_MENUSTARTINDEX, String.valueOf(m));
                    }
                    break;
                }
            }
        }catch (GenericMessageException duplicateEx){
            //２－１．処理２で重複Exceptionが発生した場合は下記の処理を行う。
            if(!params.containsKey(RPK_DUPLICATE_EX)){
                //b.処理データ値Mapへ処理２の重複Exceptionを"duplicateEx"をキーに格納する。
                params.put(RPK_DUPLICATE_EX, duplicateEx);
                params.put(RPK_DUPLICATE_MGROUPCD, targetMenuGroup);
                params.put(RPK_DUPLICATE_MENUCD, targetMenuCd);
            }
        }
        //４．パラメーター.[[管理対象メニュー]]を処理データ値Mapへ格納する。
        params.put(RPK_KANRI_MENU, listTargetMenu);
        //５．エラー対象インデックスを設定。
        putDuplicateParamIndex(params, listTargetMenu);
    }
    /**
     * 食包材設定処理
     * 
     * １．事前条件判断処理 
     * 　　　パラメーター. 選択対象食包材コードがnullか、
     * 　　　またはパラメーター.[[管理対象メニュー]]がnullの場合はnullリターン。
     * ２．リターン値Mapをインスタンス化する。
     * ３．パラメーター.メニューグループコードと同じメニューグループコードを保持している
     * 　　パラメーター.[[管理対象メニュー]]の件数分下記の処理を行う。
     * 　　３－１．ユーティル【static 処理保持クラス】食包材設定処理を実行する。
     *     
     * ４．リターン値Mapをリターンする。
     * 
     * @param ckanriNo          管理番号
     * @param targetMenuGroup メニューグループコード
     * @param targetShokuCd　　 選択対象食包材コード
     * @param targetShokuName　 選択対象食包材名称
     * @param listTargetMenu    [[管理対象メニュー]]
     * @return　Map
     */
    public static Map updateShokuhouzaiAll(String ckanriNo, String targetMenuGroup, String targetShokuCd, String targetShokuName, List listTargetMenu) {
        //１．事前条件判断処理
        if(targetShokuCd == null || listTargetMenu == null){
            //パラメーター. 選択対象食包材コードがnullか、またはパラメーター.[[管理対象メニュー]]がnullの場合は
            //nullをリターンする。
            return null;
        }
        //２．リターン値Mapをインスタンス化する。
        Map params = new HashMap();
        //３．パラメーター.メニューグループコードと同じメニューグループコードを保持している
        //    パラメーター.[[管理対象メニュー]]の件数分下記の処理を行う。
        for(int m=0; m<listTargetMenu.size(); m++){
            UIKanriMenu entityMenu = (UIKanriMenu)listTargetMenu.get(m);
            if(targetMenuGroup.equals(entityMenu.getMenuGroup())){
                //３－１．ユーティル【static 処理保持クラス】食包材設定処理を実行する。
                updateShokuhouzai(params, ckanriNo, targetMenuGroup
                        , targetShokuCd, targetShokuName
                        , listTargetMenu, entityMenu.getMenuCd());
            }
        }
        //４．リターン値Mapをリターンする。
        return params;
        
    }
    /**
     * 食包材重複チェック
     * 
     * @param targetMenuGroup
     * @param targetMenuCd
     * @param targetMenuName
     * @param targetShokuhouzaiCd
     * @param targetShokuhouzaiName
     * @param listTargetMenu
     */
    public static void checkDuplicateShokuhouzai(String targetMenuGroup
            , String targetMenuCd, String targetShokuhouzaiCd, List listTargetMenu) {
        if(targetMenuGroup == null || targetMenuCd == null || targetShokuhouzaiCd == null || listTargetMenu == null){
            return;
        }
        for(int i=0; i<listTargetMenu.size(); i++){
            UIKanriMenu entity = (UIKanriMenu)listTargetMenu.get(i);
            if(!targetMenuGroup.equals(entity.getMenuGroup())
                    && targetMenuCd.equals(entity.getMenuCd())
                    && targetShokuhouzaiCd.equals(entity.getShokuCd())
                    ){
                //MSG【E0204】指定したメッセージのみ出力されます。
                throw new GenericMessageException(
                        "同一のメニューと食包材の組合せが既に登録されています。(" +
                        "メニュー："+targetMenuCd+" "+entity.getMenuNameKj()+" " +
                        "食包材：" +targetShokuhouzaiCd+" "+entity.getShokuNameKna()+
                        ")", "strConvValue", 0);
            }
        }
        
    }
    /**
     * メニュー削除処理
     * 
     * 引数DTOの[[管理対象メニュー]]のリストから
     * 削除対象メニューグループコード＆削除対象メニューコードの行を削除します。
     * 
     * @param dto
     */
    public static void removeMenu(MosChickenMstRegistDto dto){
        String targetMenuGroup=dto.getTargetMenuGroupCd();
        String targetMenuCd = dto.getTargetMenuCd();
        List listTargetMenu = dto.getListKanriMenu();
        listTargetMenu = removeMenu(targetMenuGroup, targetMenuCd, listTargetMenu);
        dto.setListKanriMenu(listTargetMenu);
    }
    /**
     * メニュー削除処理
     * 
     * 引数[[管理対象メニュー]]のリストから
     * 削除対象メニューグループコード＆削除対象メニューコードの行を削除します。
     * 
     * @param targetMenuGroup
     * @param targetMenuCd
     * @param listTargetMenu [[管理対象メニュー]]
     */
    public static List removeMenu(String targetMenuGroup, List listTargetMenu){
        if(targetMenuGroup == null || listTargetMenu == null){
            return listTargetMenu;
        }
        for(int i=0; i<listTargetMenu.size(); i++){
            UIKanriMenu entity = (UIKanriMenu)listTargetMenu.get(i);
            if(targetMenuGroup.equals(entity.getMenuGroup())){
                //削除対象メニューグループコード＆削除対象メニューコードの行を削除する。
                listTargetMenu.remove(i);
                i--;
            }
        }
        return listTargetMenu;
    }
    /**
     * メニュー削除処理
     * 
     * 引数[[管理対象メニュー]]のリストから
     * 削除対象メニューグループコード＆削除対象メニューコードの行を削除します。
     * 
     * @param targetMenuGroup
     * @param targetMenuCd
     * @param listTargetMenu [[管理対象メニュー]]
     */
    public static List removeMenu(String targetMenuGroup, String targetMenuCd, List listTargetMenu){
        if(targetMenuGroup == null || listTargetMenu == null){
            return listTargetMenu;
        }
        for(int i=0; i<listTargetMenu.size(); i++){
            UIKanriMenu entity = (UIKanriMenu)listTargetMenu.get(i);
            if(targetMenuGroup.equals(entity.getMenuGroup()) && targetMenuCd.equals(entity.getMenuCd())){
                //削除対象メニューグループコード＆削除対象メニューコードの行を削除する。
                listTargetMenu.remove(i);
                i--;
            }
        }
        return listTargetMenu;
    }
    /**
     * 
     * 食包材クリア(削除)処理
     * 
     * 引数DTOの[[管理対象メニュー]]のリストから
     * 削除対象メニューグループコード＆削除対象メニューコードの
     * 行の食包材コードと名称を空する。
     * 
     * @param dto
     * @return
     */
    public static void clearShokuhouzai(MosChickenMstRegistDto dto) {
        String targetMenuGroup =dto.getTargetMenuGroupCd();
        String targetMenuCd = dto.getTargetMenuCd();
        List listTargetMenu = dto.getListKanriMenu();
        clearShokuhouzai(targetMenuGroup, targetMenuCd, listTargetMenu);
    }
    /**
     * 食包材クリア(削除)処理
     * 
     * [[管理対象メニュー]]のリストから削除対象メニューグループコード＆削除対象メニューコードの
     * 食包材コードと名称を空する。
     * 
     * @param targetMenuGroup
     * @param targetMenuCd
     * @param listTargetMenu クリア対象リスト
     * @return
     */
    public static List clearShokuhouzai(String targetMenuGroup, String targetMenuCd, List listTargetMenu) {
        if(targetMenuGroup == null || listTargetMenu == null){
            return listTargetMenu;
        }
        for(int i=0; i<listTargetMenu.size(); i++){
            UIKanriMenu entity = (UIKanriMenu)listTargetMenu.get(i);
            if(targetMenuGroup.equals(entity.getMenuGroup()) && targetMenuCd.equals(entity.getMenuCd())){
                //削除対象メニューグループコード＆削除対象メニューコードの食包材コードと名称を空する。
                entity.setShokuCd("");
                entity.setShokuNameKna("");
                //再設定する。
                listTargetMenu.set(i, entity);
            }
        }
        return listTargetMenu;
    }
    /**
     * メニュー存在チェック
     * 
     * @param dto
     */
    public static void checkingExisMenu(MosChickenMstRegistDto dto){
        String targetMenuGroup =dto.getTargetMenuGroupCd();
        if(!MosChickenMstRegistUtil.checkExisMenu(targetMenuGroup, dto.getListKanriMenu())) {
            int menuGroupIndex = Integer.valueOf(dto.getTargetMGroupIndex()).intValue();
            //MSG【E0204】汎用
            throw new GenericMessageException("メニューを追加してください。", "btMenuAdd"
                    , menuGroupIndex);
        }
    }
    /**
     * メニュー存在チェック
     * 
     * @param targetMenuGroup 処理対象メニューグループコード
     * @param listTargetMenu
     */
    public static boolean checkExisMenu(String targetMenuGroup, List listTargetMenu){
        if(listTargetMenu !=null && targetMenuGroup != null){
            for(int m=0; m<listTargetMenu.size(); m++){
                UIKanriMenu entityMenu = (UIKanriMenu)listTargetMenu.get(m);
                if(targetMenuGroup.equals(entityMenu.getMenuGroup())){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 対象メニューコード存在チェック
     * 
     * @param targetMenuGroup
     * @param targetMenuCd
     * @param listTargetMenu
     * @return
     */
    public static boolean checkExisMenuCd(String targetMenuGroup, String targetMenuCd, List listTargetMenu){
        if(listTargetMenu !=null && targetMenuGroup != null){
            for(int m=0; m<listTargetMenu.size(); m++){
                UIKanriMenu entityMenu = (UIKanriMenu)listTargetMenu.get(m);
                if(targetMenuGroup.equals(entityMenu.getMenuGroup())){
                    if(targetMenuCd.equals(entityMenu.getMenuCd())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * 入力値チェック処理
     * 
     * @param listKikan
     * @param listTargetMenuGroup
     * @param listTargetMenu
     */
    public static void checkingInputParam(MosChickenMstRegistDto dto){
        //[[管理対象期間]]チェック処理
        try{
            //対象データ：管理対象期間
            MstKanriKikan entityKikan = (MstKanriKikan)dto.getListKanriKikan().get(0);
            checkKikan(entityKikan, dto.getSysDate());
        }catch(ApplicationException appEx){
            dto.setCompliteActionType("init");
            throw appEx;
        }
        List listMenuGroup = dto.getListKanriMenuGroup();
        List listMenu = dto.getListKanriMenu();
        //[[管理対象メニューグループ]]チェック処理
        try{
            checkMenuGroup(listMenuGroup, listMenu);
        }catch(ApplicationException appEx){
            dto.setCompliteActionType("");
            throw appEx;
        }
        //[[管理対象メニュー]]チェック処理
        try{
            checkMenu(listMenu);
        }catch(ApplicationException appEx){
            dto.setCompliteActionType("");
            throw appEx;
        }
    }
    /**
     * 管理対象期間入力値チェック処理
     * 
     * @param entityKikan 管理対象期間エンティティ
     */
    public static void checkKikan(MstKanriKikan entityKikan, String sysDate){
        //タイトル
        String title = entityKikan.getTitle();
        if(MosChickenMstRegistUtil.isNull(title)){
            throw new NotNullException("タイトル", "title", 0);
        }
        ZenkakuVerifier zenkakuVeri = new ZenkakuVerifier();
        if(!zenkakuVeri.validate(title) || title.trim().length() > 20){
            //MSG【W0605】なければなりません。
            throw new ConstraintsViolationException("タイトルは全角20文字以内で", "title", 0);
        }
        
        //日付バリデータ
        DateVerifier dateVeri = new DateVerifier();
        //日付変換
        DateFormatter dateFormat = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_NORMAL);
        //対象期間FROM
        String targetFrom = dateFormat.format(entityKikan.getTargetFrom(), false);
        if(MosChickenMstRegistUtil.isNull(targetFrom)){
            throw new NotNullException("対象期間の開始日", "targetFrom", 0);
        }
        else if(!dateVeri.validate(targetFrom)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("対象期間の開始日", "targetFrom", 0);
        }
        else if(sysDate.compareTo(targetFrom) >= 0){
            //未来日でない場合。
            //MSG【W0605】なければなりません。
            throw new ConstraintsViolationException("対象期間は先日付で", "targetFrom", 0);
        }
        //対象期間TO
        String targetTo = dateFormat.format(entityKikan.getTargetTo(), false);
        if(MosChickenMstRegistUtil.isNull(targetTo)){
            throw new NotNullException("対象期間の終了日", "targetTo", 0);
        }
        else if(!dateVeri.validate(targetTo)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("対象期間の終了日", "targetTo", 0);
        }
        //開始日、終了日の妥当性チェック
        if(targetFrom.compareTo(targetTo) > 0){
            //MSG【W0605】なければなりません。
            throw new ConstraintsViolationException("対象期間は開始日≦終了日で", "targetFrom", 0);
        }
        //デフォルト表示期間FROM
        String defaultFrom = dateFormat.format(entityKikan.getDefaultFrom(), false);
        if(MosChickenMstRegistUtil.isNull(defaultFrom)){
            throw new NotNullException("デフォルト表示期間の開始日", "defaultFrom", 0);
        }
        else if(!dateVeri.validate(defaultFrom)){
            throw new InvalidInputException("デフォルト表示期間の開始日", "defaultFrom", 0);
        }
        //デフォルト表示期間TO
        String defaultTo = dateFormat.format(entityKikan.getDefaultTo(), false);
        if(MosChickenMstRegistUtil.isNull(defaultTo)){
            throw new NotNullException("デフォルト表示期間の終了日", "defaultTo", 0);
        }
        else if(!dateVeri.validate(defaultTo)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("デフォルト表示期間の終了日", "defaultTo", 0);
        }
        //開始日、終了日の妥当性チェック
        if(defaultFrom.compareTo(defaultTo) > 0){
            //MSG【W0605】なければなりません。
            throw new ConstraintsViolationException("デフォルト表示期間は開始日≦終了日で", "defaultFrom", 0);
        }
        //デフォルト表示期間の妥当性チェック
        if(!MosChickenMstRegistUtil.isNull(defaultFrom) && !MosChickenMstRegistUtil.isNull(defaultTo)){
            if(!(defaultFrom.compareTo(targetFrom) >= 0 && defaultTo.compareTo(targetTo) <=0)){
                 //MSG【W0605】なければなりません。
                throw new ConstraintsViolationException("デフォルト表示期間は対象期間の範囲内で", "defaultFrom", 0);
            }
        }
    }
    /**
     * 管理対象メニューグループ入力値チェック処理
     * 
     * @param listMenuGroup List [[管理対象メニューグループ]]リスト
     */
    public static void checkMenuGroup(List listMenuGroup, List listMenu){         
        for(int i=0; i<listMenuGroup.size(); i++){
            //対象データ：管理対象メニューグループ
            MstKanriMenuGroup entity = (MstKanriMenuGroup)listMenuGroup.get(i);
            //商品グループ名称
            String menuGroupNm = entity.getMenuGroupNm();
            if(MosChickenMstRegistUtil.isNull(menuGroupNm)){
                throw new NotNullException("商品グループ名称", "menuGroupNm", i);
            }
            ZenkakuVerifier zenkakuVeri = new ZenkakuVerifier();
            if(!zenkakuVeri.validate(menuGroupNm) || menuGroupNm.trim().length() > 10){
                //MSG【W0605】なければなりません。
                throw new ConstraintsViolationException("商品グループ名称は全角10文字以内で", "menuGroupNm", i);
            }
            String menuGroupCd = entity.getMenuGroup();
            int menuCnt = cntMenu(menuGroupCd, listMenu);
            if(menuCnt < 1){
                //MSG【E0204】汎用
                throw new GenericMessageException("商品グループ："+menuGroupNm.trim()+" の対象メニューが登録されていません。", "menuGroupNm", i);
            }
        }
    }
    /**
     * 管理対象メニュー入力値チェック処理
     * 
     * @param listMenu List [[管理対象メニュー]]リスト
     */
    public static void checkMenu(List listMenu){
        NumericVerifier numVer = new NumericVerifier(false, 3, 0);
        if(listMenu == null || listMenu.size() <= 0) {
            //MSG【E0204】汎用
            throw new GenericMessageException("対象メニューが登録されていません。", "menuGroupNm", 0);
        }
        for(int i=0; i<listMenu.size(); i++){
            //対象データ：管理対象メニューグループ
            UIKanriMenu entity = (UIKanriMenu)listMenu.get(i);
            //食包材必須チェック 2012/09/26追加
            if(MosChickenMstRegistUtil.isNull(entity.getShokuCd())) {
            	throw new NoInputException("対象メニュー「"+entity.getMenuNameKj().trim()+"」の食包材", "strConvValue", i);
            }
            //換算値
            try{
                String inputConvValue = entity.getStrConvValue();
                if(MosChickenMstRegistUtil.isNull(inputConvValue)){
                    throw new NotNullException("換算値", "strConvValue", i);
                }
                BigDecimal convValue = new BigDecimal(inputConvValue);
                if(convValue == null){
                    throw new NotNullException("換算値", "strConvValue", i);
                }
                else if(convValue.compareTo(new BigDecimal("0")) == 0){
                    //MSG【W0605】なければなりません。
                    throw new ConstraintsViolationException("換算値は 換算値＞0 で", "strConvValue", i);
                }
                else if(!numVer.validate(convValue)){   //MSG【W0605】なければなりません。
                    //MSG【E0507】を入力してください。
                    throw new NoInputException("換算値は整数3桁以内の数値", "strConvValue", i);
                }
            }
            catch(NumberFormatException numEx){
                //MSG【E0507】を入力してください。
                throw new NoInputException("換算値は整数3桁以内の数値", "strConvValue", i);
            }
        }
    }
    /**
     * 処理対象メニューコードインデックス取得処理
     * 
     * @param targetMenuGroup
     * @param listTargetMenuGroup
     * @return int 処理対象メニューコードインデックス
     */
    public static int getTargetMGroupIndex(String targetMenuGroup, List listTargetMenuGroup){
        int targetIndex = -1;
        if(targetMenuGroup == null || listTargetMenuGroup == null){
            return targetIndex;
        }
        for(int i=0; i<listTargetMenuGroup.size(); i++){
            MstKanriMenuGroup entity = (MstKanriMenuGroup)listTargetMenuGroup.get(i);
            if(targetMenuGroup.equals(entity.getMenuGroup())){
                targetIndex = i;
                break;
            }
        }
        return targetIndex;
    }
    /**
     * 処理対象メニューコードインデックスDTO設定処理
     * @param dto
     */
    public static void settingTargetMGroupIndex(MosChickenMstRegistDto dto){
        String targetMenuGroup =dto.getTargetMenuGroupCd();
        List listTargetMenuGroup = dto.getListKanriMenuGroup();
        int targetIndex = getTargetMGroupIndex(targetMenuGroup, listTargetMenuGroup);
        dto.setTargetMGroupIndex(String.valueOf(targetIndex));
    }
    /**
     * ソート処理
     * 
     * １．メニューグループコード
     * ２．集計グループコード
     * ３．食包材コード
     * ４．メニューコード
     * 
     * @param dto
     */
    public static void sort(MosChickenMstRegistDto dto){
        List listMenu = dto.getListKanriMenu();
        if(listMenu == null || listMenu.size() <= 0){
            return;
        }
        /**
         * ソートクラス
         * 
         * ≪ソート順≫
         * ※任意の管理番号だけのデータという前提
         *  1.メニューグループコード
         *  2.集計グループ
         *  3.食包材コード
         *  4.メニューコード
         */
        Comparator sortComparator = new Comparator() {
            public boolean equals(Object obj) {
                return (super.equals(obj));
            }
            public int compare(Object obj1, Object obj2) {
                String val1 = getSortVal(obj1);               
                String val2 = getSortVal(obj2);
                
                return val1.compareTo(val2);
            }
            /**
             * ソート順判断値生成取得処理
             * 
             * @param obj
             * @return
             */
            private String getSortVal(Object obj){
                String val = "";
                String menuGroup = ((UIKanriMenu) obj).getMenuGroup();
                String sumGroup = ((UIKanriMenu) obj).getSumGroup();
                String shokuCd = ((UIKanriMenu) obj).getShokuCd();
                String menuCd = ((UIKanriMenu) obj).getMenuCd();
                /*
                 * 食包材の空チェック
                 * 
                 * 食包材は指定されない場合があるのでNullまたは空の場合は
                 * '00000'をソート順判断値の食包材コードへ設定します。
                 */
                if(shokuCd == null || shokuCd.trim().length() ==0){
                    shokuCd = "00000";
                }
                val = menuGroup
                    + sumGroup
                    + shokuCd
                    + menuCd;
                return val;
            }
        };
        Collections.sort(listMenu, sortComparator);
        dto.setListKanriMenu(listMenu);
    }
}
