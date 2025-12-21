select
	BM.MISE_CD, BM.MISE_NAME_KJ,BT.SHU_ERR_FLG 
from
	BT80CMJU BT
left join ( select MISE_CD, MISE_NAME_KJ 
            from BM01TENM where SIBU_CD = /*sibuCd*/
                 and COMPANY_CD = '00')
              BM
	       on (BT.MISE_CD = BM.MISE_CD)	
where BT.COMPANY_CD = '00'
                and BT.CMP_NO = /*cmpNo*/           
                and BT.MISE_CD = BM.MISE_CD
order by BM.MISE_CD
