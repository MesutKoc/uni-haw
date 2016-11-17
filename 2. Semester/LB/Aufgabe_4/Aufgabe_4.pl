%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                                                      %
%   Author: Mesut & Anton                              %
%   Prof: Herr Klauck (HAW)                            %
%   Aufgabe: 4                                         %
%                                                      %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%**************************************************************
% Aufgabe 4.1.1)                                              *
% Funktion: is_sbin_tree(X)                                   *
% Beschreibung:                                               *
% �berpr�ft, ob gegebenes Term syntaktisch Korrekt ist        *
%**************************************************************
is_sbin_tree(nil).                                             % Leerer Baum ist ein Baum
is_sbin_tree(t(X,Y,Z)):- is_sbin_tree_kl(Y,X),                 % Wert kleiner gleich?
                         is_sbin_tree_g(Z,X),                  % Wert gr��er?
                         is_sbin_tree(Y),                      % Rekursion f�r Y
                         is_sbin_tree(Z).                      % Rekursion f�r Z

%**************************************************************
% Funktion: is_sbin_tree_lt(Tree,LT)                          *
% Beschreibung:                                               *
% �berpr�ft, ob gegebener Wert kleiner gleich ist             *
%**************************************************************
is_sbin_tree_kl(nil,_LT).                                     % Basisfall
is_sbin_tree_kl(t(X,Y,Z),LT):- X =< LT,                       % Gegebener Wert kleiner gleich?
                               is_sbin_tree_kl(Y,LT),         % Gehe alle F�lle durch
                               is_sbin_tree_kl(Z,LT).         % Gehe alle F�lle durch
                               
%**************************************************************
% Funktion: is_sbin_tree_lt(Tree,LX)                          *
% Beschreibung:                                               *
% �berpr�ft, ob gegebener Wert gr��er ist                     *
%**************************************************************
is_sbin_tree_g(nil,_LX).                                 % Basisfall
is_sbin_tree_g(t(X,Y,Z),LX):-  X > LX,                   % Gegebener Wert gr��er?
                               is_sbin_tree_g(Y,LX),     % Gehe alle F�lle durch
                               is_sbin_tree_g(Z,LX).     % Gehe alle F�lle durch

%**************************************************************
% Aufgabe 4.1.2)                                              *
% Funktion: insert_sbin_tree(Element,Tree,NewTree)            *
% Beschreibung:                                               *
% Funktion, welches Element in den Baum Tree einf�gt          *
% resultierend im neuen Baum NewTree                          *
%**************************************************************
insert_sbin_tree(E,nil,t(E,nil,nil)).                              % Basisfall
insert_sbin_tree(E,t(X,Y,Z),t(X,Y,NT)):- E > X,                    % Ist das einzuf�gende Element gr�0er als Wertbau,?
                                         insert_sbin_tree(E,Z,NT). % Rekursion
                                         
insert_sbin_tree(E,t(X,Y,Z),t(X,NT,Z)):- E=<X,                     % Ist das einzuf�gende Element kleiner ?
                                         insert_sbin_tree(E,Y,NT). % Rekursion

%**************************************************************
% Aufgabe 4.1.3)                                              *
% Funktion: inorder_sbin2tree(Tree, List)                     *
% Beschreibung:                                               *
% Funktion, welches den Baum Tree in Inorder-Reihenfolge      *
%traversiert und die Elemente in die Liste List schreibt      *
%**************************************************************
inorder_sbin2tree(nil, []).                                                 % Basisfall
inorder_sbin2tree(t(Wert,LT,RT), List):- X1 = t(Wert,LT,RT),                % Zuweisung des Funktors
                                         is_sbin_tree(X1),!,                % �berpr�fen, ob der Baum ein syntaktisch korrekter Form ist
                                         inorder_sbin2tree(LT,LTNeu),       % Rekursion f�r LT
                                         inorder_sbin2tree(RT, RTNeu),      % Rekursion f�r RT
                                         append(LTNeu,[Wert|RTNeu],List).   % Zu einer Liste
                                         
%**************************************************************
% Aufgabe 4.1.4)                                              *
% Funktion: list2sbin_tree(List, Tree)                        *
% Beschreibung:                                               *
% Funktion, welches eine Liste List mit ganzen Zahlen in einen*
% sortierten Bin�rbaum von obiger Gestalt transformiert.      *
%**************************************************************
list2sbin_tree([],nil).                                              % Basisfall
list2sbin_tree(List,t(X,Y,Z)):- halfList(List,FirstHalf,LastHalf),   % gleich gro�e Listen aufteilen
                                last(FirstHalf,X),                   % Der Fall, wo der Wert das letzte Element der Liste ist
                                append(FirstRes,[X],FirstHalf),      % Wenn so, dann mach FirstRes und last Elem zu einer Liste
                                not(permutation(FirstRes,[])),       % Die daraus resultierende Liste darf nicht leer sein // Same as FirstRes == []
                                list2sbin_tree(FirstRes,Y),          % Gehe f�r alle Elemente f�r Y der Liste durch
                                list2sbin_tree(LastHalf,Z).          % Gehe f�r alle Elemente f�r Z der Liste durch

list2sbin_tree(List,t(X,Y,nil)):- halfList(List,FirstHalf,LastHalf), % gleich gro�e Listen aufteilen
                                  last(FirstHalf,X),                 % Der Fall, wo der Wert das letzte Element der Liste ist
                                  append(FirstRes,[X],FirstHalf),    % Wenn so, dann mach FirstRes und last Elem zu einer Liste
                                  permutation(FirstRes,[]),          % Die daraus resultierende Liste ist leer
                                  list2sbin_tree(LastHalf,Y).        % Gehe rekursiv nur f�r Y durch

%**************************************************************
% Aufgabe 4.1.4)                                              *
% Funktion: halfList(List,FirstPart,SecondPart)               *
% Beschreibung:                                               *
% Funktion, das eine Liste in zwei m�glichst gleich gro�e     *
% Listen aufteilt. Der head von SecondPart w�re dann die      *
% Wurzel dieses Baums.                                        *
%**************************************************************
halfList([],[],[]).                                                                    % Basisfall
halfList(List, FirstPart,SecondPart):-  msort(List,SortedList),                        % Sortiere die Liste, ohne Duplikate zu l�schen
                                        SortedList = [First|RestList],                 % Die Sortierte Liste besteht aus First und RestList
                                        last(RestList,Last),                           % Das letzte Element der Restliste
                                        append(Result,[Last],RestList),                % F�ge es zu einer Liste zusammen
                                        halfList(Result,OtherFirstList,OtherRestList), % Rekursion f�r z.B. geschachtelte Listen
                                        FirstPart = [First|OtherFirstList],            % Der FirstPart ist der Head und der Rest ist die andere FirstListe
                                        append(OtherRestList,[Last],SecondPart).       % F�ge Sie zu einer Liste zusammen

halfList(List, FirstPart,SecondPart):- List = [First|RestList],                        % Die iste besteht aus First und RestList
                                       not(last(RestList,_Last)),                      % Der Fall, wo das letzte Element nicht das letzte Element oist
                                       FirstPart = [First],                            % In diesem Fall bleibt der FirstPart nat�rlich nur der First
                                       SecondPart = [].                                % Der SecondPart ist dann leer, weil es das letzte Elem nicht gibt.
list2sbin_tree([_F|[_X,_R]]).
%**************************************************************
% Aufgabe 4.2.1)                                              *
% Funktion: is_atomic_expr(Term)                              *
% Beschreibung:                                               *
% Funktion, welches erkennt, ob Term eine atomare             *
% logische Aussage ist                                        *
%**************************************************************
is_atomic_expr(Term) :- atom(Term). % Ist der Term ein Atom?

%**************************************************************
% Aufgabe 4.2.2)                                              *
% Funktion: is_literal(Term)                                  *
% Beschreibung:                                               *
% Funktion, welches erkennt, ob Term ein Literal ist          *
%**************************************************************
is_literal(Term)      :- is_atomic_expr(Term). % Literale sind automare Aussagen weswegen wir
is_literal(not(Term)) :- is_atomic_expr(Term). % hier es nigieren und �berpr�fen k�nnen, ob es eine atomare logische Aussage ist

%**************************************************************
% Aufgabe 4.2.3)                                              *
% Funktion: nnf_expr(Term, Simplified)                        *
% Beschreibung:                                               *
% Funktion, welches einen aussagenlogischen Term zu einem     *
% neuen aussagenlogischen Term Simplified vereinfacht,        *
% der die Negationsnormalform darstellt.                      *
%**************************************************************
nnf_expr(Term, Term) :- is_literal(Term). % Zuerst �berpr�fen, ob es �berhaupt ein Literal ist.
%**************************************************************
%  AND                                                        *
%**************************************************************
nnf_expr(and(X, Y), R) :- nnf_expr(X, XR),
                          nnf_expr(Y, YR),
                          nnf_expr(and(XR, YR),R).
                                    
%**************************************************************
%  OR                                                         *
%**************************************************************
nnf_expr(or(X, Y), or(XR, YR)) :- nnf_expr(X, XR),
                                  nnf_expr(Y, YR).
                                  
%**************************************************************
%  NAND                                                       *
%**************************************************************
nnf_expr(nand(X, Y),or(XR, YR)) :- nnf_expr( not(X), XR),
                                   nnf_expr(not(Y), YR).
%%nnf_expr(nand(X, Y),XYR)) :- nnf_expr( not(and(X,Y), XYR).

%**************************************************************
%  NOR                                                        *
%**************************************************************
nnf_expr(nor(X, Y), or(XR, YR)) :- nnf_expr(not(X), XR),
                                   nnf_expr(not(Y), YR).
                                        
%**************************************************************
%  XOR                                                        *
%(not(A)^B) v (A ^not(B))                                     *
%**************************************************************
nnf_expr(xor(X, Y), or(and(not(XR), YR), and(XR, not(YR)))) :- nnf_expr(X, XR),
                                                               nnf_expr(Y, YR).
                                                          
%**************************************************************
%  IMPL                                                       *
%**************************************************************
nnf_expr(impl(X, Y), R) :- nnf_expr(X, XR),
                           nnf_expr(Y, YR),
                           nnf_expr(or(not(XR), YR),R).
%                                          
%**************************************************************
%  EQUI                                                       *
%**************************************************************
nnf_expr(aequev(X, Y), and(or(not(XR), YR), and(XR, not(YR)))) :- nnf_expr(X, XR),
                                                                  nnf_expr(Y,YR).
%**************************************************************
%  DEMORGAN                                                   *
%**************************************************************
nnf_expr(not(not(Term)), TR) :- nnf_expr(Term, TR).

nnf_expr(not(and(X, Y)), R) :- nnf_expr(X, XR),
                               nnf_expr(Y, YR),
                               nnf_expr(or(not(XR), not(YR)),R),!.

nnf_expr(not(or(X, Y)), and(XR, YR)) :- nnf_expr(not(X), XR),
                                        nnf_expr(not(Y), YR).

%**************************************************************
% Aufgabe 4.2.4)                                              *
% Funktion: is_clause(Term)                                   *
% Beschreibung:                                               *
% Funktion, welches testet, ob Term eine Klausel ist          *
%**************************************************************
is_clause(Term) :- nnf_expr(Term, Result),           % N�tig, da zum Beispiel: not(and(a, b)) muss gebrochen werden [DeMorgan]
                   is_clause_(Result).               % Rufe Helper auf

is_clause_(Term)     :- is_literal(Term).         % �berpr�fe, ob gegebener Term ein Literal ist
is_clause_(or(A, B)) :- is_clause(A),             % Sofern in Disjunktion, dann gehe rekursiv f�r A
                        is_clause(B).             % Sofern in Disjunktion, dann gehe rekursiv f�r B
                       
%**************************************************************
% Aufgabe 4.2.5)                                              *
% Funktion: is_horn_clause(Term)                              *
% Beschreibung:                                               *
% Funktion, welches testet, ob Term eine Horn-Klausel ist     *
%**************************************************************
is_horn_clause(Term) :-  is_horn_clause(Term, Count),          % Rufe Helper auf
                         Count =< 1.                           % Es darf nur ein maximal positives Literal vorkommen -> sonst keine Horn-Klausel
                         
is_horn_clause(not(Term), 0) :-    is_atomic_expr(Term), !.        % Wenn negierter Term, dann �berpr�fe ob Literal ist und Counte 0
is_horn_clause(Term, 1) :-         is_atomic_expr(Term), !.        % Wenn positiver Term, dann �berpr�fe ob Literal ist und Counte 1
is_horn_clause(or(X, Y), Count) :- is_horn_clause(X, CountX), % Gehe rekursiv f�r X
                                   is_horn_clause(Y, CountY), % Gehe rekursiv f�r Y
                                   Count is (CountX + CountY).
