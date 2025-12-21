select
    BT32.TOTAL_LAST_YEAR,
    BT32.TOTAL_LAST_KAI,
    sum(case when BT32.TOTAL_RESULT = '3' or BT32.TOTAL_RESULT = '4' then 1 else 0 end) as GOKAKU,
    sum(case when BT32.TOTAL_RESULT = '2' then 1 else 0 end) as HUGOKAKU,
    sum(case when BT32.TOTAL_RESULT = '1' then 1 else 0 end) as HORYU,
    sum(case when BT32.TOTAL_RESULT = '0' then 1 else 0 end) as MUKOU
from
    BT32MLKR as BT32
    inner join BM12STAF as BM12 on BM12.STAFF_ID = BT32.STAFF_ID
    inner join BM01TENM as BM01 on BM01.COMPANY_CD = BM12.COMPANY_CD and BM01.MISE_CD = BM12.MISE_CD_1
    inner join BR18ENTD as BR18 
		on BR18.ENTRY_CD = '10' and BR18.ENTRY_YEAR = BT32.TOTAL_LAST_YEAR 
		and BR18.ENTRY_KAI = BT32.TOTAL_LAST_KAI
		and BR18.USERTYPE_CD = /*userTypeCd*/'01' 
       and BR18.DAY_KBN = '05' and BR18.FROM_DT <= /*sysDate*/'20060825'
where
    /*IF dto.entryFlg == 0 */
    BT32.TOTAL_LAST_YEAR = /*dto.entryYear*/'2005' and
    BT32.TOTAL_LAST_KAI = /*dto.entryKai*/'002'
    /*END*/
    /*IF dto.entryFlg == 1 */
    BT32.TOTAL_LAST_YEAR = /*dto.entryYear*/'2005'
    /*END*/
    /*IF dto.entryFlg == 2 */
    BT32.TOTAL_LAST_YEAR IN (/*dto.bandleYear1*/'2006', /*dto.bandleYear2*/'2005', /*dto.bandleYear3*/'2004') and
    BR18.FROM_DT <= /*sysDate*/'20060810'
    /*END*/
    /*IF dto.entryFlg != 2 && dto.selectFlg == 0 && dto.areaDai != null*/
    and BM01.AREA_DAI = /*dto.areaDai*/'011'
    /*END*/
    /*IF userTypeCd == '02' || (dto.entryFlg != 2 && dto.selectFlg == 1) */
    and BM12.ONER_CD = /*dto.onerCd*/'36003'
    /*END*/
    /*IF dto.entryFlg != 2 && dto.selectFlg == 2 && dto.miseCd != null*/
    and BM12.MISE_CD_1 = /*dto.miseCd*/'00014'
    /*END*/
group by
    BT32.TOTAL_LAST_YEAR,
    BT32.TOTAL_LAST_KAI
order by
    BT32.TOTAL_LAST_YEAR,
    BT32.TOTAL_LAST_KAI
