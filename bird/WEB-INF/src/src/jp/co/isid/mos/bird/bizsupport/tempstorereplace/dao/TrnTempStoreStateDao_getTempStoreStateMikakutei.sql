select
    BT44INN.SET_FLG,
    BT44.SIBU_CD,
    rtrim(BM10.SIBU_NAME) as SIBU_NAME,
    BT44.FC_RC,
    BT44INN.KARI_CD,
    BT44.YOSAN_DT,
    BT44.YOSAN,
    BT44INN.KAKUTEI_DT,
    (
        select MIN(BT63.EIGYO_DT)
          from BT63SNIP BT63
         where BT63.COMPANY_CD = /*companyCd*/'00'
           and BT63.EIGYO_DT between /*fromDt*/'20060401' and /*toDt*/'20070331'
           and BT63.URIAGE > 0
           and BT63.MISE_CD = BT44.MISE_CD

    ) as URIAGE_DT,
    BT44INN.OPEN_DT,
    BT44INN.CLOSE_DT,
    BT44INN.MISE_KBN,
    BT44INN.SIBU_CD as JITU_SIBU_CD,
    rtrim(BT44INN.SIBU_NAME) as JITU_SIBU_NAME,
    BT44INN.MISE_CD,
    rtrim(BT44INN.MISE_NAME_KJ) as MISE_NAME_KJ,
    rtrim(BT44INN.FIRST_USER) as FIRST_USER,
    rtrim(BT44INN.USER_NAME_KJ) as USER_NAME_KJ,
    BT44INN.FIRST_TMSP
from
    BT44MSJY as BT44
    inner join BM10GSIB as BM10 on BM10.COMPANY_CD = BT44.COMPANY_CD and BM10.SIBU_CD = BT44.SIBU_CD
    inner join (
        select
            BT43.COMPANY_CD,
            BT43.KARI_CD,
            BT43.SET_FLG,
            BT43.KAKUTEI_DT,
            BM01.OPEN_DT,
            BM01.CLOSE_DT,
            BM01.MISE_KBN,
            BM01.SIBU_CD,
            BM10JITU.SIBU_NAME,
            BT43.MISE_CD,
            BM01.MISE_NAME_KJ,
            BT43.FIRST_USER,
            BR01.USER_NAME_KJ,
            BT43.FIRST_TMSP 
        from
            BT43KTKT as BT43
            inner join BM01TENM as BM01 on BM01.COMPANY_CD = BT43.COMPANY_CD and BM01.MISE_CD = BT43.MISE_CD
            inner join BM10GSIB as BM10JITU on BM10JITU.COMPANY_CD = BT43.COMPANY_CD and BM10JITU.SIBU_CD = BM01.SIBU_CD
            left outer join BR01USER as BR01 on BR01.USER_ID = BT43.FIRST_USER
        where
            BT43.NENDO = /*nendo*/'2006'
    ) as BT44INN on BT44INN.COMPANY_CD = BT44.COMPANY_CD and BT44INN.KARI_CD = BT44.MISE_CD

where
    BT44.YOSAN_DT = (
		
        select
            MIN(YOSAN_DT)
        from
            BT44MSJY
        where
            YOSAN_DT between /*fromDt*/'200604' and /*toDt*/'200703' and
            COMPANY_CD = BT44.COMPANY_CD and
            MISE_CD = BT44.MISE_CD and
            YOSAN <> 0
    ) and
    BT44.COMPANY_CD = /*companyCd*/'00' and
    BT44.MISE_CD like 'X%'
	/*IF targetCd.equals("1")*/
	and BM10.JIGYOU_CD = /*code*/'11000'
	/*END*/
	/*IF targetCd.equals("2")*/
	and BM10.SIBU_CD = /*code*/'011'
	/*END*/
order by
    BT44.SIBU_CD,
    BT44.MISE_CD


