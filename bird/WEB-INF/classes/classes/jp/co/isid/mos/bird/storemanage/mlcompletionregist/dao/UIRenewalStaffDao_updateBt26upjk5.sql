update BT26UPJK

set RENEW5_ENT_YEAR  = /*entity.entryYear*/'' 
,   RENEW5_ENT_KAI  = /*entity.entryKai*/'' 
,   RENEW5_COURSE  = /*entity.compleCourseCd*/''
,   RENEW5_COURSE_NAME  = /*entity.compleCourseName*/''
,   RENEW5_DATE  = /*entity.compleCourseDt*/''
,   RENEW5_STATUS  = /*entity.courseStatus*/''
,   LAST_USER  = /*entity.lastUser*/''
,   LAST_PGM  = /*entity.lastPgm*/'BSM006'
,   LAST_TMSP  = /*entity.lastTmsp*/

where STAFF_ID = /*entity.staffId*/''
and   LICENSE_DT = /*entity.licenseDt*/''
and   RENEW5_STATUS <> '1'