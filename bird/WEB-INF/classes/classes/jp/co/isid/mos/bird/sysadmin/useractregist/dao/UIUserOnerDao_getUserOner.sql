select bm06.user_id, bm06.company_cd, bm06.oner_cd, br01.user_Name_Kj, br01.user_Name_Kana  
from bm06uonr bm06, br01user br01 
where 
bm06.user_id = br01.user_id and 
bm06.company_cd = /*companyCd*/ and 
bm06.oner_cd = /*onerCd*/ and
bm06.user_id <> /*userId*/ and
br01.stop_flg <> '1' and
br01.sekyu_flg = '1'
order by bm06.company_cd, bm06.oner_cd, br01.user_Name_Kana