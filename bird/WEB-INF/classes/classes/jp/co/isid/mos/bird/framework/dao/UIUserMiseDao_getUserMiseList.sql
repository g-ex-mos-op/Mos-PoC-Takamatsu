select bm07.user_id, bm07.company_cd, bm07.mise_cd, br01.user_Name_Kj, br01.user_Name_Kana  
from bm07uten bm07, br01user br01 
where 
bm07.user_id = br01.user_id and 
bm07.company_cd = /*companyCd*/ and 
bm07.mise_cd = /*miseCd*/ and
bm07.user_id <> /*userId*/ and
br01.stop_flg <> '1'
order by bm07.company_cd, bm07.mise_cd, br01.user_Name_Kana