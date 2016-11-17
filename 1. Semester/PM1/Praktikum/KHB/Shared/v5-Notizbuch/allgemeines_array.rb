matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
#matrix = []

matrix[0].length()

zwei_dim_ary = [[1,2,3,4],[1,2,3,4,6,7],[1,2,3]]
l = zwei_dim_ary[0].length()

#for i in 0...zwei_dim_ary.size()
#  #p zwei_dim_ary[i]
#  for j in 0...zwei_dim_ary[i].size()
#    print "#{zwei_dim_ary[i][j]} "
#  end
#  puts "|"
#end

def z2_ary_ausgeben(ary)
  ary.each(){ |zeile|
    zeile.each{ | elem |
      print "#{elem} "
    }
    puts "|"
  }
end

zwei_dim_ary = [[1,2,3,4],[1,2,3,4,6,7],[1,2,3]]

# Ersetzen Sie in zwei_dim_ary alle Zeilen, die eine 6 enthalten durch ein leeres Array

zwei_dim_ary.each_index(){ |index|

  if zwei_dim_ary[index].include?(1)
    zwei_dim_ary[index] = []
  end

}

zwei_dim_ary.each{ |zeile|

  if zeile.include?(1)
   zeile = []
  end

}
# Ersetzen Sie in zwei_dim_ary alle Vorkommen von 1 durch "korrupt"

zwei_dim_ary = [[1,2],[1],[1,5,7]]
  
zwei_dim_ary.each(){ |zeile|
  zeile.each_index{|index|
    if zeile[index] == 1
      zeile[index] = "korrupt"
    end
  }
}

# "korrupt" -> 1000
# Lösung doppelt indiziert nicht zu empfehlen 
# arbeitet wie unter Zeile 49
puts "korrupt -> 1000"
zwei_dim_ary.each_index{ |z_index|
  zwei_dim_ary[z_index].each_index{|s_index|
    if zwei_dim_ary[z_index][s_index] == "korrupt"
      zwei_dim_ary[z_index][s_index] = 1000
    end
  }
}

z2_ary_ausgeben(zwei_dim_ary)

## keine Ersetzung in zwei_dim_ary sondern nur  neue Zuweisung für lokale Variable elem
#  
#zwei_dim_ary.each(){ |zeile|
#  zeile.each{|elem|
#    if elem == 1
#      puts "elem #{elem} detected"
#      elem = "korrupt"
#    end
#  }
#}
#
#z2_ary_ausgeben(zwei_dim_ary)
#
## Referenz auf selbes Objekt
#puts "Kaputt gespielt"
#spalte = [1,2,3]
#
#z_d_a = [spalte,spalte,spalte]
#
#z_d_a[0][0] = "korrupt"
#
#
#spalte[1] = "auch korrupt"
#
#z2_ary_ausgeben(z_d_a)
#
#z = 7
#ary = [z,z,z]
#
#ary[0] = 9
#p ary

