select  
    br01user.user_id 
   ,br01user.user_name_kj 
   ,br01user.user_name_kana 
   ,bm03uscp.r_company_cd 
   ,bc02comp.company_name 
   ,br01user.usertype_cd 
   ,bc04ustp.usertype_name
   ,bm01tenm.mise_cd 
   ,bm01tenm.mise_name_kj as sosiki
from 
  br01user inner join bm03uscp on br01user.user_id=bm03uscp.user_id 
           inner join bc02comp on bc02comp.r_company_cd=bm03uscp.r_company_cd
           inner join bc04ustp on br01user.usertype_cd=bc04ustp.usertype_cd
           inner join bm07uten on br01user.user_id=bm07uten.user_id
           inner join bm01tenm on bm07uten.mise_cd=bm01tenm.mise_cd and bm07uten.company_cd=bm01tenm.company_cd
where 
     bm07uten.user_id = br01user.user_id  
and  bm07uten.company_cd = /*COMPANY_CD*/'00'
and  bm07uten.mise_cd 
        in (
			    SELECT distinct
			       bm01.mise_cd
			    from 
			      bm01tenm bm01
			    where 
			          bm01.company_cd = /*COMPANY_CD*/'00'
			    AND   bm01.oner_cd = /*ONER_CD*/'36001'
            )
/*IF STOP_FLG != null */ AND br01user.stop_flg <> '1' /*END*/
order by user_id