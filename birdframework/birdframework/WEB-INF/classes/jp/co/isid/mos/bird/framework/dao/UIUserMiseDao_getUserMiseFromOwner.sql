select  
 bm07.user_id, 
 bm07.company_cd, 
 bm07.mise_cd, 
 br01.user_Name_Kj, 
 br01.user_Name_Kana
from 
 bm07uten bm07, br01user br01 
where 
     bm07.user_id = br01.user_id  
and  bm07.company_cd = /*companyCd*/'00'
and  bm07.mise_cd 
        in (
			    SELECT distinct
			       bm01.mise_cd
			    from 
			      bm01tenm bm01
			    where 
			          bm01.company_cd = /*companyCd*/
			    AND   bm01.oner_cd = /*ownerCd*/
            )
and  bm07.user_id <> /*userId*/ 
and  br01.stop_flg <> '1'
