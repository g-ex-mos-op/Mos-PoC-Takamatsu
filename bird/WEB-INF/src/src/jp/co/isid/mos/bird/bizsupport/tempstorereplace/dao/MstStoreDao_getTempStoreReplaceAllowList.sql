select MISE_CD
  from BT44MSJY BT44
 where COMPANY_CD = /*companyCd*/'00'
   and MISE_CD not like 'X%'
   and exists(
select MISE_CD
  from BM01TENM BM01
 where COMPANY_CD = /*companyCd*/'00'
   and BM01.MISE_CD = BT44.MISE_CD
)
 group by MISE_CD