select
    BT44.MISE_CD as KARI_CD,
    BT44.YOSAN_DT,
    BT44.YOSAN
from BT44MSJY BT44,
(
	select MISE_CD,
	       MIN(YOSAN_DT) as YOSAN_DT
	  from BT44MSJY
	 where YOSAN_DT between /*fromDt*/'200604' and /*toDt*/'200703' and
	       COMPANY_CD = /*companyCd*/'00' and
	       MISE_CD like 'X%' and
	       YOSAN <> 0
	 group by MISE_CD
	 order by MISE_CD
) INN
where
    BT44.YOSAN_DT = INN.YOSAN_DT and
    BT44.MISE_CD = INN.MISE_CD
order by
    KARI_CD,
    YOSAN_DT