package jp.co.isid.mos.bird.entry.projectplanmstregist.logic.impl;

import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.UICourseInfo;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.CheckInputDataLogic;
import jp.co.isid.mos.bird.entry.projectplanmstregist.util.ProjectPlanMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuVerifier;

/**
 * 事業方針説明会マスタ登録
 * 
 * 入力値チェックロジック
 * 
 * @author xkinu
 *
 */
public class CheckInputDataLogicImpl implements CheckInputDataLogic {
    /** ロジックID */
    public static final String LOGIC_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"L03";
    /**
     * パラメーターKey： 管理マスタリスト
     */
    public static final String PK_ENTITY_COURSE = "entityCourse";
    /**
     * パラメーターKey： 修正箇所制御フラグ
     */
    public static final String PK_READONLY_FLG = "readOnlyFlg";
    
    /**
     * 事前条件処理
     * 
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //パラメーター対象事業方針説明会情報
        UICourseInfo entity = (UICourseInfo)params.get(PK_ENTITY_COURSE);
        if (entity == null) {
            throw new MissingDataException("管理マスタ情報");
        }
        //パラメーター編集不可判断フラグ必須チェック
        if(params.get(PK_READONLY_FLG) == null){
            throw new MissingDataException("修正箇所制御フラグ");
        }
        //日付変換
        DateFormatter dateFormat = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_NORMAL);
        //開催日必須チェック
        String execDt = dateFormat.format(entity.getExecDt(), false);
        if(ProjectPlanMstRegistUtil.isNull(execDt)){
            throw new NotNullException("開催日", "execDt", 0);
        }
        //説明会名称必須チェック
        String title = entity.getEntryTitle();
        if(ProjectPlanMstRegistUtil.isNull(title)){
            throw new NotNullException("説明会名称", "entryTitle", 0);
        }
        //オーナー：表示開始日必須チェック
        String onerDispFrom = dateFormat.format(entity.getOnerDispFrom(), false);
        if(ProjectPlanMstRegistUtil.isNull(onerDispFrom)){
            throw new NotNullException("オーナー：表示開始日", "onerDispFrom", 0);
        }
        //オーナー表示終了日必須チェック
        String onerDispTo = dateFormat.format(entity.getOnerDispTo(), false);
        if(ProjectPlanMstRegistUtil.isNull(onerDispTo)){
            throw new NotNullException("オーナー：表示終了日", "onerDispTo", 0);
        }
        //オーナー：申込開始日必須チェック
        String onerEntryFrom = dateFormat.format(entity.getOnerEntryFrom(), false);
        if(ProjectPlanMstRegistUtil.isNull(onerEntryFrom)){
            throw new NotNullException("オーナー：申込開始日", "onerEntryFrom", 0);
        }
        //オーナー：申込終了日必須チェック
        String onerEntryTo = dateFormat.format(entity.getOnerEntryTo(), false);
        if(ProjectPlanMstRegistUtil.isNull(onerEntryTo)){
            throw new NotNullException("オーナー：申込終了日", "onerEntryTo", 0);
        }
        //本部：表示開始日必須チェック
        String dispFrom = dateFormat.format(entity.getDispFrom(), false);
        if(ProjectPlanMstRegistUtil.isNull(dispFrom)){
            throw new NotNullException("本部：表示開始日", "dispFrom", 0);
        }
        //本部：表示終了日必須チェック
        String dispTo = dateFormat.format(entity.getDispTo(), false);
        if(ProjectPlanMstRegistUtil.isNull(dispTo)){
            throw new NotNullException("本部：表示終了日", "dispTo", 0);
        }
        //本部：申込開始日必須チェック
        String entryFrom = dateFormat.format(entity.getEntryFrom(), false);
        if(ProjectPlanMstRegistUtil.isNull(entryFrom)){
            throw new NotNullException("本部：申込開始日", "entryFrom", 0);
        }
        //本部：申込終了日必須チェック
        String entryTo = dateFormat.format(entity.getEntryTo(), false);
        if(ProjectPlanMstRegistUtil.isNull(entryTo)){
            throw new NotNullException("本部：申込終了日", "entryTo", 0);
        }
        String note = entity.getNote();
        if(ProjectPlanMstRegistUtil.isNull(note)){
//            throw new NotNullException("委任状", "note", 0);
        }
        String notice1 = entity.getNotice1();
        if(ProjectPlanMstRegistUtil.isNull(notice1)){
//            throw new NotNullException("注意事項", "notice1", 0);
        }
        //日付バリデータ
        DateVerifier dateVeri = new DateVerifier();
        
        //１．開催日の日付の妥当性チェック
        if(!dateVeri.validate(execDt)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("開催日", "execDt", 0);
        }
        //全角バリデータ
        ZenkakuVerifier zenkakuVeri = new ZenkakuVerifier();
        if(!zenkakuVeri.validate(title) || title.trim().length() > 50){
            //MSG【汎用】
            throw new GenericMessageException("説明会名称は全角50文字以内で入力してください。", "entryTitle", 0);
        }
        //２．オーナー：表示開始日の日付の妥当性チェック
        if(!dateVeri.validate(onerDispFrom)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("オーナー：表示開始日", "onerDispFrom", 0);
        }
        //３．オーナー：表示終了日の日付の妥当性チェック
        if(!dateVeri.validate(onerDispTo)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("オーナー：表示終了日", "onerDispTo", 0);
        }
        //４．オーナー：申込開始日の日付の妥当性チェック
        if(!dateVeri.validate(onerEntryFrom)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("オーナー：申込開始日", "onerEntryFrom", 0);
        }
        //５．オーナー：申込終了日の日付の妥当性チェック
        if(!dateVeri.validate(onerEntryTo)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("オーナー：申込終了日", "onerEntryTo", 0);
        }
        //６．本部：表示開始日の日付の妥当性チェック
        if(!dateVeri.validate(dispFrom)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("本部：表示開始日", "dispFrom", 0);
        }
        //３．本部：表示終了日の日付の妥当性チェック
        if(!dateVeri.validate(dispTo)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("本部：表示終了日", "dispTo", 0);
        }
        //４．本部：申込開始日の日付の妥当性チェック
        if(!dateVeri.validate(entryFrom)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("本部：申込開始日", "entryFrom", 0);
        }
        //５．本部：申込終了日の日付の妥当性チェック
        if(!dateVeri.validate(entryTo)){
            //妥当性がない場合
            //MSG【E0505】’が正しくありません。’
            throw new InvalidInputException("本部：申込終了日", "entryTo", 0);
        }
        //６．委任状妥当性チェック
        if(!ProjectPlanMstRegistUtil.isNull(note)){
            if(!zenkakuVeri.validate(note) || note.trim().length() > 50){
                //MSG【汎用】
                throw new GenericMessageException("委任状は全角50文字以内で入力してください。", "note", 0);
            }
        }
        //７．注意事項妥当性チェック
        if(!ProjectPlanMstRegistUtil.isNull(notice1)){
            if(!zenkakuVeri.validate(notice1)){
                //MSG【汎用】
                throw new GenericMessageException("注意事項は全角500文字以内で入力してください。", "notice1", 0);
            }
            //改行文字個数
            notice1 = ProjectPlanMstRegistUtil.changeEnterWordJSFtoDB(notice1);
            if(notice1.length() > 500){
                //MSG【汎用】
                throw new GenericMessageException("注意事項は全角500文字以内で入力してください。", "notice1", 0);
            }
        }
        else{
            
        }
    }

    /**
     * 実行処理
     * 
     * １．事前条件処理を実行する。
     * 
     * ２．日付前後関係のエラーチェック処理を行う。
     * 
     * ３．nullをリターンする。
     * 
     * @param params
     * @return null
     */
    public Map execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //２．日付前後関係のエラーチェック処理を行う。
        checkDate(params);
        //３．nullをリターンする。
        return null;
    }
    /**
     * 日付前後関係のエラーチェック処理
     * 
     *　・下記の順番でチェックを行う。
     *   check1. オーナー表示開始日≦オーナー申込開始日になっているか？
     *   check2．オーナー申込開始日≦オーナー申込終了日になっているか？
     *   check3．オーナー申込終了日≦オーナー表示終了日になっているか？
     *   check4．本部表示開始日≦本部申込開始日になっているか？
     *   check5．本部申込開始日≦本部申込終了日になっているか？
     *   check6．本部申込終了日≦本部表示終了日になっているか？
     *   check7．本部表：示開始日≦オーナー：表示開始日になっているか？
     *   check8．本部：申込開始日≦オーナー：申込開始日になっているか？
     *   check9．オーナー：申込終了日≦本部：申込終了日になっているか？
     *   check10.オーナー：表示終了日≦本部：表示終了日になっているか？
     *   check11.開催日＞本部：申込終了日になっているか？
     *   
     * @param params
     */
    private void checkDate(Map params){
        //パラメーター対象事業方針説明会情報
        UICourseInfo entity = (UICourseInfo)params.get(PK_ENTITY_COURSE);
        /*
         * パラメーター編集不可判断フラグ必須チェック
         * true:処理対象が編集の場合で、かつ本部：申込開始日 <= システム日付の場合
         * false:上記以外
         */
        boolean readOnlyFlg = ((Boolean)params.get(PK_READONLY_FLG)).booleanValue();
        
        //開催日
        String execDt = entity.getExecDt();
        //オーナー：表示開始日
        String onerDispFrom = entity.getOnerDispFrom();
        //オーナー：表示終了日
        String onerDispTo = entity.getOnerDispTo();
        //オーナー：申込開始日
        String onerEntryFrom = entity.getOnerEntryFrom();
        //オーナー：申込終了日
        String onerEntryTo = entity.getOnerEntryTo();
        //本部：表示開始日
        String dispFrom = entity.getDispFrom();
        //本部：表示終了日
        String dispTo = entity.getDispTo();
        //本部：申込開始日
        String entryFrom = entity.getEntryFrom();
        //本部：申込終了日
        String entryTo = entity.getEntryTo();
        
        //check1．オーナー：表示開始日≦オーナー：申込開始日
        if(!readOnlyFlg && onerDispFrom.compareTo(onerEntryFrom) > 0){
            //MSG【W0606】オーナー：表示開始はオーナー：申込開始以前の日付でなければなりません。
            NotRelevantException ex = 
                new NotRelevantException("オーナー：表示開始","オーナー：申込開始以前の日付");
            ex.setHtmlElementName("onerDispFrom");
            ex.setHtmlElementIndex(0);
            throw ex;
        }
        //check2．オーナー：申込開始日≦オーナー申込終了日
        if(onerEntryFrom.compareTo(onerEntryTo) > 0){
            String msg1 = "オーナー：申込開始";
            String msg2 = "オーナー：申込終了以前の日付";
            String htmlElementKey = "onerEntryFrom";
            if(readOnlyFlg){
                msg1 = "オーナー：申込終了";
                msg2 = "オーナー：申込開始以降の日付";
                htmlElementKey = "onerEntryTo";
            }
            //MSG【W0606】msg1+ は +msg2+ でなければなりません。
            NotRelevantException ex = 
                new NotRelevantException(msg1,msg2);
            ex.setHtmlElementName(htmlElementKey);
            ex.setHtmlElementIndex(0);
            throw ex;
        }
        //check3．オーナー：申込終了日≦オーナー：表示終了日
        if(onerEntryTo.compareTo(onerDispTo) > 0){
            //MSG【W0606】オーナー：申込終了日はオーナー：表示終了以前の日付でなければなりません。
            NotRelevantException ex = 
                new NotRelevantException("オーナー：申込終了","オーナー：表示終了以前の日付");
            ex.setHtmlElementName("onerEntryTo");
            ex.setHtmlElementIndex(0);
            throw ex;
        }
        //check4．本部：表示開始日≦本部：申込開始日
        if(!readOnlyFlg && dispFrom.compareTo(entryFrom) > 0){
            //MSG【W0606】本部：表示開始は本部：申込終了以前の日付でなければなりません。
            NotRelevantException ex = 
                new NotRelevantException("本部：表示開始","本部：申込開始以前の日付");
            ex.setHtmlElementName("dispFrom");
            ex.setHtmlElementIndex(0);
            throw ex;
        }
        //check5．本部：申込開始日≦本部：申込終了日
        if(entryFrom.compareTo(entryTo) > 0){
            String msg1 = "本部：申込開始";
            String msg2 = "本部：申込終了以前の日付";
            String htmlElementKey = "entryFrom";
            if(readOnlyFlg){
                msg1 = "本部：申込終了";
                msg2 = "本部：申込開始以降の日付";
                htmlElementKey = "entryTo";
            }
            //MSG【W0606】msg1+ は +msg2+ でなければなりません。
            NotRelevantException ex = 
                new NotRelevantException(msg1,msg2);
            ex.setHtmlElementName(htmlElementKey);
             ex.setHtmlElementIndex(0);
            throw ex;
        }
        //check6．本部：申込終了日≦本部：表示終了日
        if(entryTo.compareTo(dispTo) > 0){
            //MSG【W0606】本部：申込終了日は本部：表示終了以前の日付でなければなりません。
            NotRelevantException ex = 
                new NotRelevantException("本部：申込終了","本部：表示終了以前の日付");
            ex.setHtmlElementName("entryTo");
            ex.setHtmlElementIndex(0);
            throw ex;
        }
        //check7．本部：表示開始日≦オーナー：表示開始日
        if(!readOnlyFlg && dispFrom.compareTo(onerDispFrom) > 0){
            //MSG【W0606】本部：表示開始はオーナー：表示開始以前の日付でなければなりません。
            NotRelevantException ex = 
                new NotRelevantException("本部：表示開始","オーナー：表示開始以前の日付");
            ex.setHtmlElementName("dispFrom");
            ex.setHtmlElementIndex(0);
            throw ex;
        }
        //check8．本部：申込開始日≦オーナー：申込開始日
        if(!readOnlyFlg && entryFrom.compareTo(onerEntryFrom) > 0){
            //MSG【W0606】本部：申込開始日はオーナー：申込開始以前の日付でなければなりません。
            NotRelevantException ex = 
                new NotRelevantException("本部：申込開始","オーナー：申込開始以前の日付");
            ex.setHtmlElementName("entryFrom");
            ex.setHtmlElementIndex(0);
            throw ex;
        }
        //check9．オーナー申込終了日≦本部：申込終了日
        if(onerEntryTo.compareTo(entryTo) > 0){
            //MSG【W0606】オーナー：申込終了日は本部：申込終了以前の日付でなければなりません。
            NotRelevantException ex = 
                new NotRelevantException("オーナー：申込終了","本部：申込終了以前の日付");
            ex.setHtmlElementName("onerEntryTo");
            ex.setHtmlElementIndex(0);
            throw ex;
        }
        //check10．オーナー：表示終了日≦本部：表示終了日
        if(onerDispTo.compareTo(dispTo) > 0){
            //MSG【W0606】オーナー：表示終了日は本部：表示終了以前の日付でなければなりません。
            NotRelevantException ex = 
                new NotRelevantException("オーナー：表示終了","本部：表示終了以前の日付");
            ex.setHtmlElementName("onerDispTo");
            ex.setHtmlElementIndex(0);
            throw ex;
        }
        //check10．本部：申込終了日 ＜　開催日
        if(entryTo.compareTo(execDt) >= 0){
            //MSG【W0606】オーナー：本部：申込終了日は開催日より前の日付でなければなりません。
            NotRelevantException ex = 
                new NotRelevantException("本部：申込終了","開催日より前の日付");
            ex.setHtmlElementName("entryTo");
            ex.setHtmlElementIndex(0);
            throw ex;
        }
    }
}
