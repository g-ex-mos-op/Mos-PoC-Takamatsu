package jp.co.isid.mos.bird.entry.mlentry.logic;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;

/**
 * マスターライセンス共通ロジックインターフェース
 * @author Aspac
 */
public interface MlEntryUtilLogic {
    
    /**
     * 新規エントリーレコード生成
     * ※既存エントリーデータに新規エントリー(insertFlg=true)が存在しない場合
     * 　新規エントリー入力用の空レコードを追加する。(画面に空の入力欄が表示)
     * @param MlEntryDto
     */
    public void makeNewEntryRec(MlEntryDto dto);
    
    
    /**
     * 更新対象エントリーデータに表示用エントリー者Noを割り当てる
     * ※空データを含むためStaffIDが指定されていないデータもカウントする。
     * □エントリー済み＋履歴あり
     * □エントリー済み＋履歴なし
     * □新規エントリー者
     * @param MlEntryDto
     */
    public void addEntryNo(List listEntry);
    
    
    /**
     * 更新対象エントリーデータから空データを削除する。
     * @param MlEntryDto
     * @return
     */
    public void delEntryNoDate(MlEntryDto dto);
    
    
    /**
     * エントリー状況リストデータ調整
     * ※画面の能力・筆記・面接チェックボックスから
     * ※エントリー状況テーブルに登録する各ステータスを設定
     * @param MlEntryDto
     */
    public void makeEntryListChkStates(List listEntry);
    
    
    /**
     * エントリー対象のスタッフリストを生成する。
     * @param MlEntryDto
     */
    public void makeListEntryEnableStaff(MlEntryDto dto);
    
}
