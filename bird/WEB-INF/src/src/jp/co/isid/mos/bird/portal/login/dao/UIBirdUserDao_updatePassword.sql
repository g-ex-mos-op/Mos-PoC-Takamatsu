update br01user 
set user_pswd = /*entity.userPswd*/,
    pswd_updt_dt = /*entity.pswdUpdtDt*/,
    last_pswd = /*entity.lastPswd*/, 
    last_user = /*entity.lastUser*/,
    last_pgm = /*entity.lastPgm*/,
    last_tmsp = /*entity.lastTmsp*/ 
where user_id = /*entity.userId*/