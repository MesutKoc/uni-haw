%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                                                      %
%   Author: Mesut & Anton                              %
%   Prof: Herr Klauck (HAW)                            %
%   Aufgabe: 3                                         %
%                                                      %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%**************************************************************
% Aufgabe 3.1.1)                                              *
% Funktion: vorfahre(X.Y) & nachkomme(X,Y)                    *
% Beschreibung:                                               *
% vorfahre(X,Y) (descendant) und nachkomme(X,Y) (ancestor),   *
% die alle Vorfahren bzw. Nachkommen von Y berechnen.         *
%**************************************************************
vorfahre(X,Y)  :- elternteil(X,Y).                              % Der Vorfahre von X,Y ist der Elternteil von X,Y
vorfahre(Z,X)  :- elternteil(X,Y), vorfahre(Z,Y).               % Der Vorfahre von Z,X ist der Elternteil von X,Y und der Vorfahre von Z,Y.
nachkomme(X,Y) :- vorfahre(X,Y).                                % Der Nachkomme von den Vorfahren

%**************************************************************
% Aufgabe 3.1.2)                                              *
% Funktion: geschwister(X,Y)                                  *
% Beschreibung:                                               *
% geschwister(X,Y) (siblings).                                *
% Bedingung X ungleich Y (in Prolog X \= Y)                   *
%**************************************************************
geschwister(X,Y) :- elternteil(Z,X),                           % Wenn es ein Elternteil zu Z,X gibt
                    elternteil(Z,Y),                           % Wenn es ein Elternteil zu Z,Y gibt
                    X \= Y                                    % Wenn sie UNGLEICH sind
                    .                                         % Setze gezielt hier den Cut ein

%**************************************************************
% Aufgabe 3.1.3)                                              *
% Funktion: bruder(X,Y) & schwester(X,Y)                      *
% Beschreibung:                                               *
% geschwister(X,Y) die beiden Prädikate bruder(X,Y) und       *
% schwester(X,Y).                                             *
%**************************************************************
bruder(X,Y)    :- maennlich(X), geschwister(X,Y).    % Bruder ist es, wenn X männlich und X,Y geschwister sind
schwester(X,Y) :- weiblich(X), geschwister(X,Y).     % Schwester ist es, wenn X weiblich und X,Y geschwister sind

%**************************************************************
% Aufgabe 3.1.4)                                              *
% Funktion: eheleute(X,Y) mit symmetrischer Eigenschaft       *
% Beschreibung:                                               *
% Unter der Verwendung von verheiratet realisieren wir bei    *
% dem Prädikat eheleute die symmetrische Eigenschaft          *
%**************************************************************
eheleute(X,Y) :- verheiratet(X,Y).    % Eheleute sind Personen, die Verheiratet sind
eheleute(Y,X) :- verheiratet(Y,X).    % Hier ist die symmetrie genauestens zu sehen: Y,X sind eheleute, wenn Y,X verheiratet sind.

%**************************************************************
% Aufgabe 3.1.5)                                              *
% Funktion: oma(X,Y) & opa(X,Y)                               *
% Beschreibung:                                               *
% Verwandtschaftsstufen (oma,opa) die Prädikat uropa(X,Y)     *
% und uroma(X,Y)                                              *
%**************************************************************
opa(X,Y)   :- maennlich(X),                 % Opa muss männlich sein
              elternteil(X,B),              % um den Vorfahren zu
              elternteil(B,Y).              % bestimmen

oma(X,Y)   :- weiblich(X),                  % Oma muss weiblich sein
              elternteil(X,B),              % um den Vorfahren zu
              elternteil(B,Y).              % bestimmen

%**************************************************************
% Aufruf der Prädikate uropa & uroma                          *
% Mit Hilfe der Transitivität bestimmen wir den               *
%**************************************************************
uropa(X,Y) :- opa(X,A),elternteil(A,Y).  % Aufruf durch die Verwandsschaftsstufe Opa(X,Y).
uroma(X,Y) :- oma(X,A),elternteil(A,Y).  % Aufruf durch die Verwandsschaftsstufe Oma(X,Y).

%**************************************************************
% Aufgabe 3.2.6)                                              *
% Funktionen: maenUweibl/1, verhKor/1, elterVoll/1            *
%             wurzel/1                                        *
% Beschreibung:                                               *
% Prädikate zur Konsistenzprüfung der Datenbank               *
% (maenUweibl/1, verhKor/1, elterVoll/1, wurzel/1)            *
%**************************************************************
maenUweibl(OtStream) :- findall(X, (maennlich(X), weiblich(X)), OtStream).                                  % Finde eine Person, die zugleich männlich und weiblich ist.
verhKor(OtStream)    :- findall([X,Y], (verheiratet(X,Y), weiblich(X), maennlich(Y)), OtStream).            % Ist das Prädikat verheiratet korrekt abgespeichert? (links Mann, rechts Frau)

elterVoll(OtStream)  :- findall(X, (elternteil(X,_Y), not(maennlich(X)), not(weiblich(X))), OtStreamX),     % Sind alle Personen im Prädikat elternteil auch als männlich oder weiblich registriert?
                        findall(Y, (elternteil(_X,Y), not(maennlich(Y)), not(weiblich(Y))), OtStreamY),     % Ausgabeliste
                        append(OtStreamX, OtStreamY, OtStream).                                             % listen zusammenfügen von den vorherigen Outputlists.

wurzel(OtStream)    :- forall(findall(X, (elternteil(X,_),not(elternteil(_,X))),OtStream),print(OtStream)). % Findet alle die keine Eltern haben.

%**************************************************************
% Aufgabe 3.2.2)                                              *
% Funktion: oc(X,Y)                                           *
% Beschreibung:                                               *
% Occorus Check                                               *
%**************************************************************
% oc2(X, Term) :- var(Term),                % Ist der 1. Parameter eine Variable?
%                 Term == X, !,             % Sind die gleich?
%                 fail.                     % Fail.
% 
oc2(X, Term) :- var(Term),                  % Ist der 1. Parameter eine Variable?
                Term \== X, !.              % Sind sie ungleich?

oc2(X, Term) :- nonvar(Term),               % Ist der 1. Paramter keine Variable?
                Term =.. [_|List],          % Zusammlegung
                oc_iter(X, List).           % Iterationsaufruf

%**************************************************************
% Aufgabe 3.2.2)                                              *
% Funktion: oc_iter                                           *
% Beschreibung:                                               *
% Occorus Check Iterator                                      *
%**************************************************************
oc_iter(_, []).
oc_iter(X, [H|T]) :- oc2(X, H),
                     oc_iter(X, T).

%**************************************************************
% Aufgabe 3.2.2)                                              *
% Funktion: list_iter2                                        *
% Beschreibung:                                               *
% Unify Iterator                                              *
%**************************************************************
list_iter2([], []).
list_iter2([K1|R1], [K2|R2]) :- unify(K1, K2),
                                list_iter2(R1, R2).

%**************************************************************
% Aufgabe 3.2.2)                                              *
% Funktion: unify(..)                                         *
% Beschreibung:                                               *
% Der Fall, wo beide Terme Variablen sind                     *
% Beispiel: unify(X,Y)                                        *
%**************************************************************
unify(Term1, Term2) :- var(Term1),          % Ist Term1 eine Variable?
                       var(Term2), !,       % Ist Term2 eine Variable?
                       Term1 = Term2.       % Substitution

%**************************************************************
% Aufgabe 3.2.2)                                              *
% Funktion: unify(..)                                         *
% Beschreibung:                                               *
% Der Fall, wo das 1.Term eine Varible ist, 2. keine Var.     *
% Beispiel: unify(X,y)                                        *
%**************************************************************
unify(Term1, Term2) :- var(Term1),         % Ist Term1 eine Variable?
                       nonvar(Term2), !,   % Und Term2 keine Variable?
                       oc2(Term1, Term2),  % Rufe OC auf
                       Term1 = Term2.      % Substuiere

%**************************************************************
% Aufgabe 3.2.2)                                              *
% Funktion: unify(..)                                         *
% Beschreibung:                                               *
% Der Fall, wo das 1.Term eine Varible ist, 2. keine Var.     *
% Beispiel: unify(x,Y)                                        *
%**************************************************************
unify(Term1, Term2) :- nonvar(Term1),         % Ist Term1 eine Variable?
                       var(Term2), !,         % Und Term2 keine Variable?
                       oc2(Term2, Term1),     % Rufe OC auf
                       Term2 = Term1.         % Substuiere
%**************************************************************
% Aufgabe 3.2.2)                                              *
% Funktion: unify(..)                                         *
% Beschreibung:                                               *
% Der Fall, wo wir von einer Liste ausgehen                   *
% Beispiel: unify([X],[x])                                    *
%**************************************************************
unify(Term1, Term2) :- nonvar(Term1),            % Ist Term1 keine Variable?
                       nonvar(Term2), !,         % Und Term2 genauso? Also gehen wir von einer Liste aus?
                       Term1 =.. [K|ArgList],    % Zusammlegung
                       Term2 =.. [K|Arg2List],   % Zusammlegung
                       length(ArgList,Laenge1),  % Länge der List1 herausfinden
                       length(Arg2List,Laenge2), % Länge der List2 herausfinden
                       Laenge1 == Laenge2,       % p^k = p ^k
                       list_iter2(ArgList,Arg2List).