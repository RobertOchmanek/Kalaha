# TO-DO:

### Zasady:
- ~~Ruchy wykonuje się w kierunku przeciwnym do kierunku ruchu wskazówek zegara.~~
- ~~Gracz wyjmuje wszystkie kamienie z dowolnego dołka w rzędzie własnym, a następnie wkłada po kolei po jednym do kolejnych dołków, poczynając od znajdującego się po prawej stronie tego, z którego wyjął (również do bazowych).~~
- ~~Jeśli ostatni kamień wpadł do własnej bazy, gracz wykonuje jeszcze jeden ruch. Powtarza się to dopóty ostatni kamień nie wpadnie do innego dołka.~~
- ~~Jeśli ostatni kamień wpadł do własnego pustego dołka, gracz bierze wszystkie kamienie z leżącego naprzeciw dołka przeciwnika i wkłada je do swojej bazy.~~
- ~~Gra kończy się gdy jeden z graczy nie może wykonać ruchu (wszystkie dołki po jego stronie są puste gdy przypada na niego kolej ruchu).~~
- ~~Na koniec, przeciwnik zbiera resztę kamieni ze swoich dołków i wkłada do swojej bazy. Wygrywa ten, kto uzbierał więcej kamieni w swej bazie. W przypadku równej ich liczby – remis.~~

### Wymagania:
- ~~Partię zaczyna zawsze gracz pierwszy.~~
- ~~Gra toczy się do wygranej jednej ze stron bądź remisu.~~
- ~~Przekazanie przez gracza błędnej informacji o jego ruchu należy zignorować i zapytać gracza ponownie.~~
- ~~Zakładamy, że gracze zawsze w jakiś sposób odpowiedzą na pytanie o ruch. Choć czas do namysłu może być dowolny. W czasie gdy gracz planuje posunięcie obiekt gry nic nie robi.~~
- **Gra musi przekazywać graczom czy obserwatorom dane w sposób uniemożliwiający ich modyfikację (ma być możliwy tylko odczyt).**
- ~~Gra działa sekwencyjnie, dodatkowe wątki nie są w niej potrzebne.~~
- ~~Gra nie potrzebuje grafiki - przeciwnikami są programy.~~
- **Użytkownik nie zamierza popsuć gry ustawiając zamiast wymaganych obiektów `null`.**
- **Gry muszą być od siebie niezależne: jednoczesne utworzenie wielu obiektów gry powinno umożliwić niezależną rozgrywkę.**
- **Stan planszy przekazywany do graczy zależy od położenia gracza. Numeracja dołków wg. obrazka, gdzie przedstawiono numerację z punktu widzenia gracza żółtego.**
- ~~Dołki na pozycjach od 0 do "liczba dołków"-1 to dołki należące do aktualnego gracza. Dołek o numerze "liczba dołków" to baza aktualnego gracza.~~
- **Obserwatorzy zawsze otrzymują stan planszy z punktu widzenia gracza pierwszego.**
- ~~Metoda setVariant ustawia liczbę dołków. Chodzi o dołki, w których umieszczane są kamienie.~~