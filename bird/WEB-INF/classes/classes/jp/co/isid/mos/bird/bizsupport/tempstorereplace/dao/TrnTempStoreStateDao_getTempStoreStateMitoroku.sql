select
    '' as SET_FLG,
    BT44.SIBU_CD,
    rtrim(BM10.SIBU_NAME) as SIBU_NAME,
    BT44.FC_RC,
    BT44.MISE_CD as KARI_CD,
    '' as YOSAN_DT,
    '' as YOSAN,
    '' as KAKUTEI_DT,
    (
        select MIN(BT63.EIGYO_DT)
          from BT63SNIP BT63
         where BT63.COMPANY_CD = /*companyCd*/'00'
           and BT63.EIGYO_DT between /*fromDt*/'200604' and /*toDt*/'200703'
           and BT63.URIAGE > 0
           and BT63.MISE_CD = BT44.MISE_CD

    ) as URIAGE_DT,
    '' as OPEN_DT,
    '' as CLOSE_DT,
    '' as MISE_KBN,
    '' as JITU_SIBU_CD,
    '' as JITU_SIBU_NAME,
    '' as MISE_CD,
    '' as MISE_NAME_KJ,
    '' as FIRST_USER,
    '' as USER_NAME_KJ,
    cast(null as TIMESTAMP) as FIRST_TMSP
from
    BT44MSJY as BT44
    inner join BM10GSIB as BM10 on BM10.COMPANY_CD = BT44.COMPANY_CD and BM10.SIBU_CD = BT44.SIBU_CD
    left outer join BR01USER as BR01 on BR01.USER_ID = BT44.FIRST_USER
where
    BT44.COMPANY_CD = /*companyCd*/'00' and
    BT44.MISE_CD like 'X%' and
    BT44.YOSAN_DT between /*fromDt*/'200604' and /*toDt*/'200703' and
    BT44.MISE_CD not in(
        select KARI_CD from BT43KTKT
        where NENDO = /*nendo*/'2006' and
        COMPANY_CD = BT44.COMPANY_CD
    )
	/*IF targetCd.equals("1")*/
	and BM10.JIGYOU_CD = /*code*/'11000'
	/*END*/
	/*IF targetCd.equals("2")*/
	and BM10.SIBU_CD = /*code*/'011'
	/*END*/
group by
    BT44.SIBU_CD,
    BM10.SIBU_NAME,
    BT44.FC_RC,
    BT44.MISE_CD
order by
    BT44.SIBU_CD,
    BT44.MISE_CD
