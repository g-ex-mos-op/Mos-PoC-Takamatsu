update BT26UPJK

set RENEW2_ENT_YEAR  = /*entity.entryYear*/'' 
,   RENEW2_ENT_KAI  = /*entity.entryKai*/'' 
,   RENEW2_COURSE  = /*entity.compleCourseCd*/''
,   RENEW2_COURSE_NAME  = /*entity.compleCourseName*/''
,   RENEW2_DATE  = /*entity.compleCourseDt*/''
,   RENEW2_STATUS  = /*entity.courseStatus*/''
,   LAST_USER  = /*entity.lastUser*/''
,   LAST_PGM  = /*entity.lastPgm*/'BSM006'
,   LAST_TMSP  = /*entity.lastTmsp*/

where STAFF_ID = /*entity.staffId*/''
and   LICENSE_DT = /*entity.licenseDt*/''
and   RENEW2_STATUS <> '1'