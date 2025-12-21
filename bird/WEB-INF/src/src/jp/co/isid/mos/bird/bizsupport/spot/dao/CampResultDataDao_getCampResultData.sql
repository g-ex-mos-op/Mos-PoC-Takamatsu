select CMP_FROM,
       CMP_TO,
       POS_FROM_DT,
       POS_END_DT,
      (select max(SYS_DT) from BT80CMJU  where CMP_NO = /*campNo*/
                        and MISE_CD in ( select MISE_CD from BM01TENM where SIBU_CD = /*sibuCd*/ )
                        and COMPANY_CD = '00')
      AS SYS_DT  
from BM53CPJK  
where CMP_NO = /*campNo*/
 