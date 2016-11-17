$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
require 'ext_pr1_v4'

# Purpose:    Die Funktion set_clock für verschieden Anzeigen erweitern
# Contrackt:  set_clock::=(now_time, future_time, isAnalog)
#             :: Array[Nat,Nat,Nat] x Array[Nat,Nat,Nat] x Bool ->? Array[Nat,Nat,Nat] ::
#             ((isModus)?(now_time[0] < LIMIT_D_HOUR) and (now_time[1] < LIMIT_MIN) and (now_time[2] < LIMIT_SEC)))::
# Test: {
#           [19,0,0],[2,0,0],12=>[9,0,0], [14,59,59],[0,1,1],24 => [15,1,0], 
#           [14,15,59],[0,15,1],12 => [2,31,0], [15,15,59],[2,15,1],24 => [17,31,0], 
#           [15,10],[3,5,10] => Err, [15,41,10],[3,5] => Err, [-15,41,10],[3,5,7] => Err,    
#       }

#------------GLOBAL VAR:----------------------
LIMIT_D_HOUR = 24
LIMIT_A_HOUR = 12
LIMIT_MIN = 60
LIMIT_SEC = 60
#------------CONVERT SECONDS:----------------------
def time_in_sec(time)
(((time[0] * LIMIT_MIN ) + time[1] ) * LIMIT_SEC) + time[2]
end

def set_clock_analog(now_time, future_time, isModus)
  #------------PRE COND (isModus):----------------------
  check_pre(((isModus == 12) or (isModus == 24)))
  limit_h = (isModus == 12)?(LIMIT_A_HOUR):(LIMIT_D_HOUR)
  #------------PRE COND (Array's):----------------------
  check_pre((now_time.array? and future_time.array?))
  check_pre(((now_time.size == 3) and (future_time.size == 3)))
  check_pre((now_time[0-3].nat? and future_time[0-3].nat?))
  check_pre(((now_time[0] < LIMIT_D_HOUR) and (now_time[1] < LIMIT_MIN) and (now_time[2] < LIMIT_SEC)))
  check_pre((future_time[1] < LIMIT_MIN and (future_time[2] < LIMIT_SEC)))
  #------------KONVERSION:----------------------
  sum_time_sec = time_in_sec(now_time) + time_in_sec(future_time)
  #-------------SAVE:----------------------
  h = now_time[0] + future_time[0]
  m = now_time[1] + future_time[1]
  #-------------REKONVERSION:----------------------
  hour = (sum_time_sec / 3600) % limit_h
  min = (sum_time_sec - (h * 3600)) / 60 %LIMIT_MIN
  sec = (sum_time_sec - (h * 3600) - (m * 60)) %LIMIT_SEC
  #-------------RESULTAT:----------------------
  Array[hour, min, sec]
  
end