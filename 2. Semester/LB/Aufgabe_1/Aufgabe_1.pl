%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                                                      %
%   Author: Mesut & Anton                              %
%   Prof: Herr Klauck (HAW)                            %
%   Aufgabe: 1                                         %
%                                                      %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%**************************************
% Aufgabe 1.1)
% is_a_list(L), testet, ob L eine Prolog-Liste ist
% is_a_list ::= List ->? Bool
% Test: { is_a_list([]) => true,
%         is_a_list(1) => false,
%         is_a_list([1]) => true }
%**************************************
is_a_list([]).                           % Liste mit keinem Element ist trotzdem eine Liste.
is_a_list([_|Rest]) :- is_a_list(Rest).  % Liste mit mindestens einem Element, dessen Rest ebenfalls eine Liste sein kann.

%**************************************
% Aufgabe 1.2)
% app(L1, L2, L3), konkateniert L1 und L2 zu L3
% app ::= List x List -> List
% Zu erwarten sind L1 und L2 die dann zu L3 werden.
% Test: { app([1,2],[1,2],X) => X = [1, 2, 1, 2],
%         app([],_,_) => true,
%         app([1],[2],[1,2]) => true }
%**************************************
app([],X,X).                             % Fakt 1: Wenn die Liste mit keinem Element abgefangen wird.
app(X,[],X).                             % Fakt 2: Es könnte aber L2 ebenfalls eine Liste mit keinem Element sein.
app([K|R],L2,[K|Out]) :- app(R,L2,Out).  % Regel: Wir nehmen das Kopfelement der L1 und dies ist auch unser Out.
                                         % Im Rekursionsschritt gehen wir dann den Rest1 und L2 als auch Out durch

%**************************************
% Aufgabe 1.3)
% infix(I, L), testet, ob I ein Infix der Liste L ist (vor und nach einem Infix ist noch etwas!)
% infix ::= List(Elem) x List ->? Bool
% Test: { infix([a],[a,b])   => false,
%         infix([a],[b,a,b]) => true,
%         infix([a],[b,b,a]) => false }
%**************************************
infix([Elem],[Elem,_]).                       % Wir definieren unseren Fakt, dass wenn das Elem an der Liste das Head ist, damit wir im Rekursionsschritt feststellen können, ob sich das Infix an der ersten Stelle befindet oder nicht.
infix([Elem],[_,R|R2]) :- infix(Elem,[R|R2]). % Teile die Liste und gucke durch den Rekursionsschritt die Reste in dem sich Elem befindet an.

%**************************************
% Aufgabe 1.4)
% suffix(S, L), testet, ob S ein Suffix der Liste L ist (vor einem Suffix ist noch etwas!)
% suffix ::= List(Suffix) x List ->? Bool
% Test: { suffix([a],[b,a])   => true,
%         suffix([a],[a,b])   => false,
%         suffix([a],[a])        => true }
%**************************************
suffix([Suffix],[Suffix]).                    % Fakt 1: Wenn ein Suffix und ein Suffix angegeben werden, dann entspricht das true.
suffix([Suffix],[Suffix|_List]) :- !, fail.   % Wenn das Suffix an der Kopf der Liste ist, setzen wir ein Cut ein, weil dies schon unsere Aufgabe verletzt und keine Alternativen in Frage kommen.
suffix([_Suffix],[_X|_List]) :- true.         % Wenn beliebes Element nicht an der ersten Stelle der Liste steht, dann ist alles erfüllt.

%**************************************
% Aufgabe 1.5)
% prefix(P, L), testet, ob P ein Präfix Liste L ist (nach einem Prefix ist noch etwas!)
% praefix ::= List(Praefix) x List >? Bool
% Test: { praefix([a],[a,b])   => true,
%         praefix([a],[b,a])   => false }
%**************************************
praefix([],_).                            % Wenn Liste mit keinem Element, unser Basisfall. Der Rest einer Liste kann auch eine Liste sein, deswegen rufen wir die ganze Funktion rekursiv auf, um zu überprüfen.
praefix([K|R1],[K|R2]):- praefix(R1, R2). % Wenn das Kopfelement mit der Liste 2 übereinstimmt, dann beginnt der Rekursivaufruf.

%**************************************
% Aufgabe 1.6)
% eo_count(L, Even, Odd), zählt rekursiv die Anzahl der in der Länge geraden bzw. ungeraden Listen in der Liste L inklusive dieser Listen selbst, also alle möglichen Listen!
% Eine leere Liste wird als Liste mit gerader Länge angesehen. In der Liste können Elemente vorkommen, die keine Liste sind.
%**************************************
even_list([]).                                         % event_list als Hilfsfunktion um klarzustellen, ob es um eine even List handelt.
even_list([_E1, _E2 | Rest]) :- even_list(Rest).

eo_count_helper([], 0, 0).
% Die Leere Liste beinhaltet keine weiteren Elemente.
% Die Regel wird in der Hauptfunktion eo_count aufgerufen.
% Handelt es sich um das aktuelle Element um eine Liste? Bestimme durch die Rekursion die un/geraden Elem.
% Die Anzahl der restlichen Elemente werden untersucht
eo_count_helper([First | Rest], Even, Odd) :-
                 is_a_list(First),
                 eo_count_helper(First, EvenIn, OddIn),
                 eo_count_helper(Rest, EvenRest, OddRest),
                 Even is (EvenIn + EvenRest),
                 Odd is (OddIn + OddRest).
                 
% Wenn das akt. Element keine Liste ist, wird nur der Rest angeguckt
% Wird sichergestellt durch das not(is_a_list(..)).
eo_count_helper([First | Rest], Even, Odd) :-
                 not(is_a_list(First)),
                 eo_count_helper(Rest, Even, Odd).
% Die Hauptfunktion eo_count, die unsere Methodenhelfer aufruft.
eo_count(List, Even, Odd) :-
                 eo_count_helper(List, EvenIn, OddIn),
                 ((even_list(List), Even is (EvenIn+1), Odd is OddIn,!);
                     (Even is EvenIn, Odd is (OddIn+1),!)).

%**************************************
% Aufgabe 1.7)
% del_element(E, L, R), löscht E aus der Liste L und gibt die neue Liste R zurück
% del_element ::= Element(string) x List -> List
% Test: { del_element([a],[a,b])   => true,
%         del_element([a],[b,a])   => false }
%**************************************
del_element(_,[],[]).                                       % Wir gehen von einem beliebigen Element mit leeren Listen aus. Unser Basisfall für die spätere Rekursion.
del_element(Elem1,[Elem1 | Rest1], Rest2) :- !, del_element(Elem1, Rest1, Rest2).
del_element(Elem1,[Elem2 | Rest1], [Elem2 | Rest2]):- del_element(Elem1,Rest1,Rest2).

%**************************************
% Aufgabe 1.8)
% substitute(E1,E2,L,R), vertauscht alle Vorkommen von E1 in L gegen E2 und gibt das Ergebnis in R zurück
% Test: { substitute(a,b,[a,b],R) => R = [b, b]
%         substitute(a,1,[a,b],R) => R = [1, b] }
%**************************************
substitute(_,_,[],[]).                                              % Wir gehen von beliebigen Elementen mit leeren Listen aus, unser Basisfall.
substitute(E1,E2,[K1|R],[K1|R1]) :- K1\=E1, substitute(E1,E2,R,R1). % Wenn das Kopfelement 1 dem Element ungleich ist, dann ersetze erzenende
                                                                    % Wert auf dem Rückweg im Rekursionssschritt
substitute(E1,E2,[E1|R],[E2|R1]) :- substitute(E1,E2,R,R1).         % Ist das aktuelle Element gleich wie der zu ersetzende, dann mach nichts.

%**************************************
% Aufgabe 2.2.1)
% Definition der natürlichen Zahlen als S-Zahlen:
%**************************************
nat(0).                                                             %Induktionsanfang // Rekursionsende
nat(s(N))                                                           %Induktionsbehauptung
         :- nat(N).                                                 %Induktionsschritt:  Rekursionsanfang s(x)= x+1.
         
%**************************************
% Aufgabe 2.2.1)
% nat2s(Zahl, SZahl), ein Hilfsprädikat, um natürliche Zahlen in obige Form der S-Zahlen zu bringen.
% Test: { nat2s(1,X) => X = s(0),
%         nat2s(3,X) => X = s(s(s(0))) }
%**************************************
nat2s(0, 0) :- !.             % Unser Basisfall bzw. der einfachste Fall mit dem Cut.
nat2s(X, s(Y)) :- X > 0, !,   % Wenn die natürliche Zahl (X) nicht positiv ist, dann beende bzw. mach ein Cut (effektiver).
                  E is X-1,   % Rechne X-1 und speichere es in E. (Natürliche Zahl muss von S-Zahl runtergezählt werden).
                  nat2s(E,Y). % Der Rekursionsaufruf durch unseren neuen Wert der in E ist mit Y.

%**************************************
% Aufgabe 2.2)
% add(Summand1, Summand2, Summe), welches zwei S-Zahlen addiert.
% add erwartet im Parameter zwei S-Zahlen im Form von s(..)
% Wir haben die Funktion addS genannt, damit die eigentliche Funktion von Prolog nicht überschrieben wird (add(..)).
% Test: { adds(s(0),s(0),X)    => X = s(s(0)),
%         adds(s(s(0)),s(0),X) => X = s(s(s(0)) }
%**************************************
adds(0,X,X) :- nat(X).                   % Wir überprüfen durch unseren ersten Fakt, dass die Variable X ein nat ist. Ansonsten verfälschen wir das Ergebnis, wenn wir dies nicht tun.
adds(s(R),L2,s(Out)) :- adds(R,L2,Out).  % Da wir ja im Grunde genommen mit s(..) runterzählen, rufen wir die Summieren Funktion auf, da in Relation.

%**************************************
% Aufgabe 2.3)
% sub(Minuend, Subtrahend, Differenz), welches zwei S-Zahlen subtrahiert. Falls der Subtrahend grösser als der Minuend ist, soll 0 berechnet werden.
% sub erwartet zwei S-Zahlen
% Test: { sub(s(0),s(0),X) => X = 0,
%         sub(s(s(0)),s(0),X) => X = s(0) }
%**************************************
sub(X,Y,0) :- lt(X,Y).                  % Wir überprüfen, ob dass Ergebnis eine negative Zahl sein könnte. Falls ja, dann geben wir eine 0 statt false heraus.
sub(X,Y,Z) :- adds(Z,Y,X).              % Unsere Regel 2 übergibt unsere Paramter der Summieren Funktion. (s. impl. adds(..).)

%**************************************
% Aufgabe 2.4)
% mul(Faktor1, Faktor2, Produkt), welches zwei S-Zahlen multipliziert.
% % Test: { mult(s(0),s(0),X)   => X = s(0),
%           mult(s(s(0)),s(0),X)=> X = s(s(0)) }
%**************************************
mult(0, X, 0)  :- nat(X).                        % Mit der Regel überprüfen wir, ob es sich wirklich bei X um ein nat(..) handelt.
mult(s(X),Y,Z) :- mult( X, Y, K), adds(K, Y, Z). % Wir zählem mit s(X) wieder runter und rufen rekursiv das Ergebnis welches in K gespeichert ist auf.

%**************************************
% Aufgabe 2.5)
% pow(B, E, R), welches die Rechnung BE = R implementiert.
% Test: { pow(s(s(0)),s(s(0)),X) => X = s(s(s(s(0)))) }
%**************************************
pow(B, 0, s(0))  :- nat(B).                               % Unser Basisfall. Bei 0 immer gleich.
pow(B,s(E),Erg)   :- pow( B, E, Temp), mult(Temp, B, Erg). % Speichere im Temp und rufe die Multiplikation auf.

%**************************************
% Aufgabe 2.6)
% fac(N, Factorial), welches die Fakultät N! einer S-Zahl berechnet.
% Test: { fac(s(0),X)    => X = s(0),
%         fac(s(s(0)),X) => X = s(s(0)) }
%**************************************
fac(0, s(0)).                                    % Der einfachste Basisfall, wo wir wissen, dass es immer gleich ist.
fac(s(Z), F) :- fac(Z, FZ), mult(s(Z), FZ, F),!. % Wir rufen brauche hier einen temporären Speicher den wir dann der Multiplikation-Funktion übergeben und es wieder in F speichern.
                                                 % Der Cut ist einzusetzen, weil keine Alternativen mehr gibt.
%**************************************
% Aufgabe 2.7)
% lt(N1, N2), welches N1 < N2 implementiert
% Test: { lt(s(0),s(s(0))) => true,
%         lt(s(s(0)),s(0)) => false }
%**************************************
lt(0,s(_)).                     % Unser Basisfall.
lt(s(N1),s(N2)) :- lt(N1,N2).   % Die Rekursion durch N1, N2, welche in Fakt 1 eingehen.

%**************************************
% Aufgabe 2.8)
% mod(A, B, C), welches die Modulo-Operation A mod B = C implementiert.
% Test: { mod1(s(s(0)),s(s(s(0))),X) => X = s(s(0)) }
%**************************************
mod1(A,B,A):- lt(A,B).                            % Wir überprüfen schon in unserem Basisfall, ob A < B.
mod1(A,B,C):- adds(A1,B,A), mod1(A1,B,C).         % Wir übergeben unsere Parameter an die Summieren-Funktion und danach der Rekursive Aufruf durch A1.

%**************************************
% Aufgabe 2.9)
% s2nat(SZahl, Zahl), ein Hilfsprädikat, um S-Zahlen in natürliche Zahlen zu bringen
% Test: { s2nat(s(0),X)    => X = 1
%         s2nat(s(s(0)),X) => X = 2 }
%**************************************
s2nat(0, 0).                                      % Unser Basisfall bzw. der einfachste Fall.
s2nat(s(X),Out) :- s2nat(X,NOut),Out is 1 + NOut. % Der Nachkommer muss aufgezählt werden und dementsprechend ausgegeben werden.
                                                  % Zuerst folgt der Rekursive Aufrund um es in NOut speichern zu können.