package com.java.json;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * send-engine 病例文书解析
 */
public class Test {
    
    public static void main(String[] args) throws IOException {
        
        Test t = new Test();
        
        String message = "{\n" + "\t\"docImageFormat\": \"application/html\",\n"
                + "\t\"docImageContent\": \"PHN0eWxlIHR5cGU9InRleHQvY3NzIj4gLkJzb2Z0RGVmYXVsdHtmb250LWZhbWlseTrlrovkvZM7Zm9udC1zaXplOjEwLjVwdDtsaW5lLWhlaWdodDoxLjd9IC5Cc29mdERlZmF1bHQgc3Bhbntmb250LWZhbWlseTrlrovkvZM7Zm9udC1zaXplOjEwLjVwdDtsaW5lLWhlaWdodDoxLjd9LkJzb2Z0RGVmYXVsdCBwe2ZvbnQtZmFtaWx5OuWui+S9kztmb250LXNpemU6MTAuNXB0O2xpbmUtaGVpZ2h0OjEuNzttYXJnaW46MHB4O308L3N0eWxlPjxkaXYgY2xhc3M9IkJzb2Z0RGVmYXVsdCIgc3R5bGU9IndpZHRoOjE1LjkyY207bWFyZ2luLWxlZnQ6MS41NGNtIj48ZGl2IGlkPSJoZWFkZXIiIHN0eWxlPSJmb250LWZhbWlseTrlrovkvZM7Zm9udC1zaXplOjEwLjVwdDtsaW5lLWhlaWdodDoxLjciPg0KPHAgc3R5bGU9InRleHQtYWxpZ246Y2VudGVyIj48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+DQo8L3NwYW4+PC9wPg0KPHAgc3R5bGU9InRleHQtYWxpZ246Y2VudGVyO3RleHQtYWxpZ246Y2VudGVyIj48c3BhbiBzdHlsZT0iZm9udC1zaXplOjIycHQ7Zm9udC1mYW1pbHk65Y2O5paH6KGM5qW3Ij7kuK3lsbHluILlsI/mpoTkurrmsJHljLvpmaI8L3NwYW4+PC9wPg0KPHAgc3R5bGU9InRleHQtYWxpZ246Y2VudGVyO3RleHQtYWxpZ246Y2VudGVyIj48c3BhbiBzdHlsZT0iZm9udC1zaXplOjE4cHQ7Zm9udC1mYW1pbHk65pa55q2j6IiS5L2TO2ZvbnQtd2VpZ2h0OmJvbGQiPuWNl+aWuemGq+enkeWkp+WtuDwvc3Bhbj48c3BhbiBzdHlsZT0iZm9udC1zaXplOjE0cHQ7Zm9udC13ZWlnaHQ6Ym9sZCI+6ZmE5bGe5bCP5qaE5Yy76ZmiPC9zcGFuPjwvcD4NCjxwIHN0eWxlPSJ0ZXh0LWFsaWduOmNlbnRlcjt0ZXh0LWFsaWduOmNlbnRlciI+PHNwYW4gc3R5bGU9ImZvbnQtc2l6ZToxNnB0O2ZvbnQtd2VpZ2h0OmJvbGQiPuWFpTwvc3Bhbj48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+DQo8L3NwYW4+PHNwYW4gc3R5bGU9ImZvbnQtc2l6ZToxNnB0O2ZvbnQtd2VpZ2h0OmJvbGQiPumZojwvc3Bhbj48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+DQo8L3NwYW4+PHNwYW4gc3R5bGU9ImZvbnQtc2l6ZToxNnB0O2ZvbnQtd2VpZ2h0OmJvbGQiPuiusDwvc3Bhbj48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+DQo8L3NwYW4+PHNwYW4gc3R5bGU9ImZvbnQtc2l6ZToxNnB0O2ZvbnQtd2VpZ2h0OmJvbGQiPuW9lTwvc3Bhbj48L3A+DQo8dGFibGUgY2VsbHBhZGRpbmc9IjUiIGJvcmRlcj0iMCIgcnVsZXM9ImFsbCIgc3R5bGU9InRleHQtYWxpZ246anVzdGlmeTsgdGV4dC1qdXN0aWZ5OmludGVyLWlkZW9ncmFwaDt3aWR0aDoxMDAlO2JvcmRlcjowcHQgIzAwMDAwMCBzb2xpZCAjMDAwMDAwO2JvcmRlci1jb2xsYXBzZTpjb2xsYXBzZTtlbXB0eS1jZWxsczpzaG93O3RhYmxlLWxheW91dDpmaXhlZDtib3JkZXItc3R5bGU6c29saWQiPjx0cj48dGQgIHN0eWxlPSJ3aWR0aDoxOC4wNSU7Ym9yZGVyLXJpZ2h0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1sZWZ0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci10b3A6IDAgc29saWQgIzAwMDAwMDsiPjxwIHN0eWxlPSJmb250LWhlaWdodDogMS43OyI+PHNwYW4+5aeT5ZCNOjwvc3Bhbj48c3Bhbj7lvKDljJfnkLQ8L3NwYW4+PC9wPjwvdGQ+PHRkICBzdHlsZT0id2lkdGg6MTAuNTMlO2JvcmRlci1yaWdodDogMCBzb2xpZCAjMDAwMDAwOyBib3JkZXItbGVmdDogMCBzb2xpZCAjMDAwMDAwOyBib3JkZXItdG9wOiAwIHNvbGlkICMwMDAwMDA7Ij48cCBzdHlsZT0iZm9udC1oZWlnaHQ6IDEuNzsiPjxzcGFuPuaAp+WIqzo8L3NwYW4+PHNwYW4+55S3PC9zcGFuPjwvcD48L3RkPjx0ZCAgc3R5bGU9IndpZHRoOjEzLjU0JTtib3JkZXItcmlnaHQ6IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLWxlZnQ6IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLXRvcDogMCBzb2xpZCAjMDAwMDAwOyI+PHAgc3R5bGU9ImZvbnQtaGVpZ2h0OiAxLjc7Ij48c3Bhbj7lubTpvoQ6PC9zcGFuPjxzcGFuPjY05bKBPC9zcGFuPjwvcD48L3RkPjx0ZCAgc3R5bGU9IndpZHRoOjIyLjU2JTtib3JkZXItcmlnaHQ6IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLWxlZnQ6IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLXRvcDogMCBzb2xpZCAjMDAwMDAwOyI+PHAgc3R5bGU9ImZvbnQtaGVpZ2h0OiAxLjc7Ij48c3Bhbj7np5HlrqQ6PC9zcGFuPjxzcGFuPuW/g+ihgOeuoeWGheenkeS4gOWMujwvc3Bhbj48L3A+PC90ZD48dGQgIHN0eWxlPSJ3aWR0aDoxMi43NiU7Ym9yZGVyLXJpZ2h0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1sZWZ0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci10b3A6IDAgc29saWQgIzAwMDAwMDsiPjxwIHN0eWxlPSJmb250LWhlaWdodDogMS43OyI+PHNwYW4+5bqK5Y+3Ojwvc3Bhbj48c3Bhbj4wNzA8L3NwYW4+PC9wPjwvdGQ+PHRkICBzdHlsZT0id2lkdGg6MjIuNTYlO2JvcmRlci1yaWdodDogMCBzb2xpZCAjMDAwMDAwOyBib3JkZXItbGVmdDogMCBzb2xpZCAjMDAwMDAwOyBib3JkZXItdG9wOiAwIHNvbGlkICMwMDAwMDA7Ij48cCBzdHlsZT0iZm9udC1oZWlnaHQ6IDEuNzsiPjxzcGFuPuS9j+mZouWPtzo8L3NwYW4+PHNwYW4+NTk5Mjc4PC9zcGFuPjwvcD48L3RkPjwvdHI+PC90YWJsZT4NCjxwIHN0eWxlPSJsaW5lLWhlaWdodDowLjEwMDA7bGluZS1oZWlnaHQ6MC4xMDAwO3RleHQtYWxpZ246Y2VudGVyIj48L3A+PC9kaXY+PGRpdiBpZD0ibWFpbiIgc3R5bGU9ImZvbnQtZmFtaWx5OuWui+S9kztmb250LXNpemU6MTAuNXB0O2xpbmUtaGVpZ2h0OjEuNyI+DQo8dGFibGUgY2VsbHBhZGRpbmc9IjUiIGJvcmRlcj0iMSIgcnVsZXM9ImFsbCIgc3R5bGU9InRleHQtYWxpZ246anVzdGlmeTsgdGV4dC1qdXN0aWZ5OmludGVyLWlkZW9ncmFwaDt3aWR0aDoxMDAlO2JvcmRlcjoxcHQgIzAwMDAwMCBzb2xpZCAjMDAwMDAwO2JvcmRlci1jb2xsYXBzZTpjb2xsYXBzZTtlbXB0eS1jZWxsczpzaG93O3RhYmxlLWxheW91dDpmaXhlZDtib3JkZXItc3R5bGU6c29saWQiPjx0cj48dGQgIHN0eWxlPSJ3aWR0aDo0NS45MCU7Ym9yZGVyLXJpZ2h0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1sZWZ0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1ib3R0b206IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLXRvcDogMCBzb2xpZCAjMDAwMDAwOyI+PHAgc3R5bGU9ImZvbnQtaGVpZ2h0OiAxLjc7Ij48c3Bhbj7lp5PlkI3vvJo8L3NwYW4+PHNwYW4+5byg5YyX55C0PC9zcGFuPjwvcD48L3RkPjx0ZCAgc3R5bGU9IndpZHRoOjU0LjEwJTtib3JkZXItcmlnaHQ6IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLWxlZnQ6IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLWJvdHRvbTogMCBzb2xpZCAjMDAwMDAwOyBib3JkZXItdG9wOiAwIHNvbGlkICMwMDAwMDA7Ij48cCBzdHlsZT0iZm9udC1oZWlnaHQ6IDEuNzsiPjxzcGFuPuaAp+WIq++8mjwvc3Bhbj48c3Bhbj7nlLc8L3NwYW4+PC9wPjwvdGQ+PC90cj48dHI+PHRkICBzdHlsZT0iYm9yZGVyLXJpZ2h0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1sZWZ0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1ib3R0b206IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLXRvcDogMCBzb2xpZCAjMDAwMDAwOyI+PHAgc3R5bGU9ImZvbnQtaGVpZ2h0OiAxLjc7Ij48c3Bhbj7lubTpvoTvvJo8L3NwYW4+PHNwYW4+NjTlsoE8L3NwYW4+PC9wPjwvdGQ+PHRkICBzdHlsZT0iYm9yZGVyLXJpZ2h0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1sZWZ0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1ib3R0b206IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLXRvcDogMCBzb2xpZCAjMDAwMDAwOyI+PHAgc3R5bGU9ImZvbnQtaGVpZ2h0OiAxLjc7Ij48c3BhbiBzdHlsZT0iYmdjb2xvcjo3OGI0NzgiPuWpmuWnu++8mjwvc3Bhbj48c3Bhbj7lt7LlqZo8L3NwYW4+PC9wPjwvdGQ+PC90cj48dHI+PHRkICBzdHlsZT0iYm9yZGVyLXJpZ2h0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1sZWZ0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1ib3R0b206IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLXRvcDogMCBzb2xpZCAjMDAwMDAwOyI+PHAgc3R5bGU9ImZvbnQtaGVpZ2h0OiAxLjc7Ij48c3Bhbj7msJHml4/vvJo8L3NwYW4+PHNwYW4+5rGJ5pePPC9zcGFuPjwvcD48L3RkPjx0ZCAgc3R5bGU9ImJvcmRlci1yaWdodDogMCBzb2xpZCAjMDAwMDAwOyBib3JkZXItbGVmdDogMCBzb2xpZCAjMDAwMDAwOyBib3JkZXItYm90dG9tOiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci10b3A6IDAgc29saWQgIzAwMDAwMDsiPjxwIHN0eWxlPSJmb250LWhlaWdodDogMS43OyI+PHNwYW4+6IGM5Lia77yaPC9zcGFuPjxzcGFuPuW3peS6ujwvc3Bhbj48L3A+PC90ZD48L3RyPjx0cj48dGQgIHN0eWxlPSJib3JkZXItcmlnaHQ6IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLWxlZnQ6IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLWJvdHRvbTogMCBzb2xpZCAjMDAwMDAwOyBib3JkZXItdG9wOiAwIHNvbGlkICMwMDAwMDA7Ij48cCBzdHlsZT0iZm9udC1oZWlnaHQ6IDEuNzsiPjxzcGFuPuWHuueUn+WcsO+8mjwvc3Bhbj48c3Bhbj7lub/kuJznnIHojILlkI3luILnlLXnmb3ljLrlsI/oia/plYc8L3NwYW4+PC9wPjwvdGQ+PHRkICBzdHlsZT0iYm9yZGVyLXJpZ2h0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1sZWZ0OiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci1ib3R0b206IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLXRvcDogMCBzb2xpZCAjMDAwMDAwOyI+PHAgc3R5bGU9ImZvbnQtaGVpZ2h0OiAxLjc7Ij48c3Bhbj7nl4Xlj7LpmYjov7DogIXvvJo8L3NwYW4+PHNwYW4+55eF5Lq6PC9zcGFuPjwvcD48L3RkPjwvdHI+PHRyPjx0ZCAgc3R5bGU9ImJvcmRlci1yaWdodDogMCBzb2xpZCAjMDAwMDAwOyBib3JkZXItbGVmdDogMCBzb2xpZCAjMDAwMDAwOyBib3JkZXItYm90dG9tOiAwIHNvbGlkICMwMDAwMDA7IGJvcmRlci10b3A6IDAgc29saWQgIzAwMDAwMDsiPjxwIHN0eWxlPSJmb250LWhlaWdodDogMS43OyI+PHNwYW4+5YWl6Zmi5pel5pyf77yaPC9zcGFuPjxzcGFuPjIwMjEtMDYtMTkmbmJzcDswODowMDwvc3Bhbj48L3A+PC90ZD48dGQgIHN0eWxlPSJib3JkZXItcmlnaHQ6IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLWxlZnQ6IDAgc29saWQgIzAwMDAwMDsgYm9yZGVyLWJvdHRvbTogMCBzb2xpZCAjMDAwMDAwOyBib3JkZXItdG9wOiAwIHNvbGlkICMwMDAwMDA7Ij48cCBzdHlsZT0iZm9udC1oZWlnaHQ6IDEuNzsiPjxzcGFuPuiusOW9leaXtumXtO+8mjwvc3Bhbj48c3Bhbj4yMDIxLTA2LTE5Jm5ic3A7MTI6MTI8L3NwYW4+PC9wPjwvdGQ+PC90cj48L3RhYmxlPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuS4u+ivie+8mjwvc3Bhbj48c3Bhbj7popzpnaLmta7ogr/jgIHlj4zkuIvogqLmta7ogr8xMOWkqeOAgjwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+546w55eF5Y+y77yaPC9zcGFuPjxzcGFuPuaCo+iAhTEw5aSp5YmN5peg5piO5pi+6K+x5Zug5Ye6546w6aKc6Z2i5rWu6IK/44CB5Y+M5LiL6IKi5rWu6IK/77yM5LiK6L+w55eH54q25peg5pi85aSc6IqC5b6L77yM5peg5piO5pi+57yT6Kej44CB5Yqg6YeN5Zug57Sg77yM5peg5Ly06IW56IOA44CB57qz5beu77yM5peg6IO46Ze344CB5rCU5L+D77yM5peg6IO455eb44CB5b+D5oK477yM5peg5oG25b+D44CB5ZGV5ZCQ77yM5peg5Y+N6YW444CB5Zez5rCU44CB54On5b+D5oSf77yM5peg6IW555eb77yM5Zyo5aSW5pyq5YGa54m55q6K5aSE55CG77yM5p2l6K+K5oiR6Zmi77yM5pS25YWl5oiR56eR44CC6Ieq5Y+R55eF5Lul5p2l77yM5oKj6ICF5oSf5bCP5L6/5YeP5bCR77yM5L2T6YeN5aKe5Yqg57qma2fvvIznsr7npZ7jgIHnnaHnnKDjgIHog4PnurPlpoLluLjvvIzlpKfkvr/mraPluLjjgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuaXouW+gOWPsu+8mjwvc3Bhbj48c3Bhbj7ml6LlvoDmnInpq5jooYDljovnl4XjgIHns5blsL/nl4XjgIHpo47mub/mgKflv4Pnk6Pohpznl4UmbmJzcDvmjIHnu63mgKflv4PmiL/poqTliqjnl4Xlj7LvvIzplb/mnJ/op4TlvovmnI3nlKjoja/nianvvIjljY7ms5XmnpcmbmJzcDszbWcmbmJzcDtxbuOAgeehq+mFuOawouawr+WQoeagvOmbtyZuYnNwOzc1bWcmbmJzcDtxZOOAgeWAjeS7luS5kOWFiyZuYnNwOzYuMjVtZyZuYnNwO2JpZOOAgeeri+aZruWmpSZuYnNwOzIwbWcmbmJzcDtxbuOAgeWRi+WhnuexsyZuYnNwOzIwbWcmbmJzcDtxZOOAgeieuuWGhemFryZuYnNwOzIw77yMbWcmbmJzcDtxZO+8ieayu+eWl+OAguabvuWcqOW5v+S4nOWMu+WtpumZoumZhOWxnuWMu+mZouS9j+mZou+8jOihjOWGoOiEiemAoOW9seajgOafpeaPkOekuu+8mkxN5pyq6KeB54ut56qE77yMTEFE6L+R56uv55ik5qC35omp5byg77yM5Lit5q61OTAtOTUl54ut56qE77yMVElNSeihgOa1gUlJSee6p++8jExDWOS4reauteWujOWFqOmXreWhnu+8jFRJTUnooYDmtYEw57qn77yMUkNB6L+R56uv54ut56qENTAl5Y+v6KeB5paR5Z2X77yM6L+c5q6154ut56qENjAl5Y+v6KeB5paR5Z2X77yMVElNSeihgOa1gUlJSee6p++8jOWHuumZouiviuaWreS4uu+8muWGoOW/g+eXhSZuYnNwO+W/g+aIv+mipOWKqCZuYnNwO+W/g+WKn+iDvUlJ57qn77yM6auY6KGA5Y6L55eFM+e6pyZuYnNwO+aegemrmOWNse+8jOmjjua5v+aAp+W/g+iEj+eXhSZuYnNwO+S6jOWwlueTo+S4reW6pueLreeqhOW5tuWFs+mXreS4jeWFqO+8jDLlnovns5blsL/nl4XvvIzlvZPml7bmgqPogIXmnKrlpITnkIblhqDohInnl4Xlj5jvvIzlj6PmnI3ljY7ms5XmnpfjgIHlj4zmipflj4rosIPohILnrYnoja/nianlkI7vvIzoh6rooYzoh7PkuIrmtbfluILnrKzkuIDkurrmsJHljLvpmaIyMDE2LTExLTEw6KGM5Yag6ISJ6YCg5b2x5Y+KUENJ5pyv77yM5ZyoTEFE44CBTENY572u5YWl5pSv5p625rK755aX44CC5pyJ5LqM5bCW55Oj6YeR5bGe55Oj572u5o2i5pyv55eF5Y+y44CCPC9zcGFuPjxzcGFuPuWQpuiupDwvc3Bhbj48c3Bhbj7ogp3ngo7jgIHnu5PmoLjjgIHoj4znl6LjgIHkvKTlr5I8L3NwYW4+PHNwYW4+562J5Lyg5p+T55eF5Y+y77yMJm5ic3A7PC9zcGFuPjxzcGFuPuWQpuiupDwvc3Bhbj48c3Bhbj7ovpPooYDjgIHlpJbkvKQ8L3NwYW4+PHNwYW4+5Y+y77yM5ZCm6K6k6I2v54mp6aOf54mp6L+H5pWP5Y+yLDwvc3Bhbj48c3Bhbj7pooTpmLLmjqXnp43lj7LkuI3or6Y8L3NwYW4+PHNwYW4+44CCPC9zcGFuPjwvcD4NCjxwPjxzcGFuIHN0eWxlPSJiZ2NvbG9yOjc4YjQ3ODtmb250LXdlaWdodDpib2xkIj7kuKrkurrlj7LvvJo8L3NwYW4+PHNwYW4+55Sf5LqOPC9zcGFuPjxzcGFuPuacrOWcsDwvc3Bhbj48c3Bhbj7vvIw8L3NwYW4+PHNwYW4+5ZCm6K6kPC9zcGFuPjxzcGFuPumVv+acn+WkluWcsOWxheS9j+WPsu+8jDwvc3Bhbj48c3Bhbj7lkKborqQ8L3NwYW4+PHNwYW4+55ar5Yy65bGF55WZ5Y+y77yMPC9zcGFuPjxzcGFuPuWQpuiupDwvc3Bhbj48c3Bhbj7nibnmrorljJblrablk4Hlj4rmlL7lsITnur/mjqXop6blj7LjgII8L3NwYW4+PHNwYW4+5ZCm6K6k5ZC454OfPC9zcGFuPjxzcGFuPuOAgTwvc3Bhbj48c3Bhbj7ppa7phZI8L3NwYW4+PHNwYW4+44CCPC9zcGFuPjwvcD4NCjxwPjxzcGFuIHN0eWxlPSJmb250LXdlaWdodDpib2xkIj7lqZrogrLlj7LvvJo8L3NwYW4+PHNwYW4+5bey5ama5bey6IKy77yM6YWN5YG25Y+K5aWz5YS/5YGl5bq344CCPC9zcGFuPjwvcD4NCjxwPjxzcGFuIHN0eWxlPSJmb250LXdlaWdodDpib2xkIj7lrrbml4/lj7LvvJo8L3NwYW4+PHNwYW4+5a625Lq65YGl5bq344CC5peg5a625peP6YGX5Lyg5oCn55a+55eF562J55eF5Y+y44CCPC9zcGFuPjwvcD4NCjxwIHN0eWxlPSJ0ZXh0LWFsaWduOmNlbnRlcjt0ZXh0LWFsaWduOmNlbnRlciI+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuS9kyZuYnNwOyZuYnNwO+agvCZuYnNwOyZuYnNwO+ajgCZuYnNwOyZuYnNwO+afpTwvc3Bhbj48L3A+DQo8cCBzdHlsZT0idGV4dC1hbGlnbjpjZW50ZXI7dGV4dC1hbGlnbjpjZW50ZXIiPjxzcGFuPuS9k+a4qe+8mjM2LjTihIPvvJvohInmkI/vvJo4MOasoS/liIbvvJvlkbzlkLjvvJoyMOasoS/liIbvvJvooYDljovvvJoxNDkvODltbUhnPC9zcGFuPjwvcD4NCjxwPjxzcGFuIHN0eWxlPSJmb250LXdlaWdodDpib2xkIj7kuIDoiKzmg4XlhrXvvJo8L3NwYW4+PHNwYW4+56We5b+XPC9zcGFuPjxzcGFuPua4healmjwvc3Bhbj48c3Bhbj7vvIzlj5HogrI8L3NwYW4+PHNwYW4+5q2j5bi4PC9zcGFuPjxzcGFuPu+8jDwvc3Bhbj48c3Bhbj7okKXlhbvoia/lpb08L3NwYW4+PHNwYW4+77yM5L2T5Z6LPC9zcGFuPjxzcGFuPuS4reetiTwvc3Bhbj48c3Bhbj7vvIzml6DpnaLlrrnvvIzpnaLoibI8L3NwYW4+PHNwYW4+57qi5ramPC9zcGFuPjxzcGFuPu+8jOihqOaDhTwvc3Bhbj48c3Bhbj7lronpnZk8L3NwYW4+PHNwYW4+77yMPC9zcGFuPjxzcGFuPuiHquWKqDwvc3Bhbj48c3Bhbj7kvZPkvY3vvIzmn6XkvZM8L3NwYW4+PHNwYW4+5ZCI5L2cPC9zcGFuPjxzcGFuPu+8jOWvueetlDwvc3Bhbj48c3Bhbj7liIfpopg8L3NwYW4+PHNwYW4+77yM5bmz6L2m5YWl6Zmi44CCPC9zcGFuPjwvcD4NCjxwPjxzcGFuIHN0eWxlPSJmb250LXdlaWdodDpib2xkIj7nmq7ogqTnspjohpzvvJo8L3NwYW4+PHNwYW4+6Imy5rO9PC9zcGFuPjxzcGFuPuato+W4uDwvc3Bhbj48c3Bhbj7vvIzml6Dpu4Tmn5PjgIHntKvnu4DjgIHoibLntKDmsonnnYDvvIzml6Dnmq7nlrnjgIHnmq7kuIvlh7rooYDngrnvvIzml6DnlqTnl5XjgIHnmq7kuIvnu5PoioLmiJbogr/lnZfvvIzml6Dogp3mjozjgIHonJjom5vnl6PjgIImbmJzcDvnmq7muKk8L3NwYW4+PHNwYW4+5q2j5bi4PC9zcGFuPjxzcGFuPu+8jOa5v+W6pjwvc3Bhbj48c3Bhbj7mraPluLg8L3NwYW4+PHNwYW4+77yM5by55oCnPC9zcGFuPjxzcGFuPuWlvTwvc3Bhbj48c3Bhbj7vvIzml6DmsLTogr/jgIImbmJzcDs8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPua3i+W3tOe7k++8mjwvc3Bhbj48c3Bhbj7lhajouqvmtYXooajmt4vlt7Tnu5PmnKrop6blj4rjgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuWktOmDqOWPiuWFtuWZqOWumDwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+5aS06aKF77yaPC9zcGFuPjxzcGFuPuWktOmiheaXoOeVuOW9ou+8jOaXoOiCv+Wdl+OAgeWkluS8pO+8jOWktOWPkeiJsuazveOAgeWIhuW4g+ato+W4uOOAgjwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+55y8Ojwvc3Bhbj48c3Bhbj7nnInmr5vml6DohLHokL3vvIznnavmr5vml6DlgJLnlJ/vvIzlj4zkvqfnnLznnZHmraPluLjvvIznnLznkIPml6DnqoHlh7rvvIzml6Dlh7npmbfvvIzov5Dliqjoh6rlpoLvvIznu5PohpzmraPluLjvvIzlt6nohpzml6Dpu4Tmn5PjgILlj4zkvqfnnrPlrZQ8L3NwYW4+PHNwYW4+562J5aSn562J5ZyGPC9zcGFuPjxzcGFuPu+8jDwvc3Bhbj48c3Bhbj7nnrPlrZTnm7TlvoQ8L3NwYW4+PHNwYW4+My01PC9zcGFuPjxzcGFuPm1tPC9zcGFuPjxzcGFuPu+8jDwvc3Bhbj48c3Bhbj7lr7nlhYnlj43lsITngbXmlY/vvJvovpDovo/lj43lsITngbXmlY/jgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuiAs++8mjwvc3Bhbj48c3Bhbj7lj4zkvqfogLPlu5Pml6DnlbjlvaLvvIzlj4zkvqflpJbogLPpgZPml6DlvILluLjliIbms4znianvvIzkubPnqoHml6Dljovnl5vvvIzlkKzlipvnspfmtYvmraPluLjjgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPum8u++8mjwvc3Bhbj48c3Bhbj7pvLvlpJbop4Lml6DnlbjlvaLvvIzlj4zkvqfpvLvllIfmsp/lr7nnp7DvvIzpvLvkuK3pmpTml6DlgY/mm7LvvIzpvLvohZTpgJrmsJTnlYXvvIzlj4zkvqfpvLvohZTml6DlvILluLjliIbms4znianvvIzml6DpvLvnv7znhb3liqjvvIzlia/pvLvnqqbljLrml6Dljovnl5vjgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuWPo+iFlO+8mjwvc3Bhbj48c3Bhbj7lj6PllIfnuqLmtqbvvIzlj6PohZTnspjohpzlrozmlbTvvIzlj6PohZTml6DlvILlkbPvvIzkvLjoiIzlsYXkuK3vvIzoiIzogozml6DpnIfpoqTvvIzlkr3ml6DlhYXooYDvvIzlj4zkvqfmiYHmoYPkvZPml6Dogr/lpKfvvIzml6DohJPmgKfliIbms4znianjgILlj5Hpn7PmuIXmmbDjgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPumiiOmDqO+8mjwvc3Bhbj48c3Bhbj7poojmn5Tova/vvIwmbmJzcDs8L3NwYW4+PHNwYW4+5pegPC9zcGFuPjxzcGFuPuaKteaKl+aEn+OAgumiiOWKqOiEieaXoOW8guW4uOazouWKqOWPiuadgumfs++8jOmiiOmdmeiEieaXoOaAkuW8oO+8jDwvc3Bhbj48c3Bhbj7ogp3poojpnZnohInlm57mtYHlvoHpmLTmgKc8L3NwYW4+PHNwYW4+44CC5rCU566h5bGF5Lit77yM55Sy54q26IW65peg6IK/5aSnLOaXoOihgOeuoeadgumfs+OAgjwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+6IO46YOoPC9zcGFuPjwvcD4NCjxwPjxzcGFuIHN0eWxlPSJmb250LXdlaWdodDpib2xkIj7og7jlu5PvvJo8L3NwYW4+PHNwYW4+6IO45YmN5q2j5Lit5Y+v6KeB5LiA6ZW/57qmMTBjbee6teihjOmZiOaXp+aJi+acr+eYoueXle+8jOiDuOW7k+aXoOeVuOW9ou+8jOaXoOawtOiCv+OAgeearuS4i+awlOiCv+OAgeiCv+Wdl++8jOiDuOWjgemdmeiEieaXoOabsuW8oO+8jOiCi+mXtOmameato+W4uO+8jOiDuOWjgeaXoOWOi+eXm++8jOiDuOmqqOaXoOWPqeeXm++8m+WPjOS+p+S5s+aIv+WvueensCznmq7ogqTmraPluLjvvIzlj4zkvqfkubPlpLTlr7nnp7DvvIzml6Dlh7npmbfvvIzml6DmuqLmtrLvvIzop6bor4rmnKrmiarlj4rogr/lnZfjgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuiCujwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+6KeG6K+K77yaPC9zcGFuPjxzcGFuPuWRiDwvc3Bhbj48c3Bhbj7ohbnlvI/lkbzlkLg8L3NwYW4+PHNwYW4+77yM5ZG85ZC46IqC5b6L6KeE5YiZ77yM6IKL6Ze06ZqZ5pyq6KeB5piO5pi+5byC5bi444CCPC9zcGFuPjwvcD4NCjxwPjxzcGFuIHN0eWxlPSJmb250LXdlaWdodDpib2xkIj7op6bor4rvvJo8L3NwYW4+PHNwYW4+6K+t6aKk5q2j5bi477yM5Y+M6IK65pyq6Kem5Y+K6IO46Iac5pGp5pOm5oSf77yM5pyq6Kem5Y+K55qu5LiL5o275Y+R5oSf44CCPC9zcGFuPjwvcD4NCjxwPjxzcGFuIHN0eWxlPSJmb250LXdlaWdodDpib2xkIj7lj6nor4rvvJo8L3NwYW4+PHNwYW4+5Y+M5L6n5Y+p6K+K5riF6Z+z77yM6IK65LiL55WM5L2N5LqO6IKp6IOb5LiL6KeS57q/56ysMTDogovpl7TjgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuWQrOiviu+8mjwvc3Bhbj48c3Bhbj7lj4zogrrlkbzlkLjpn7PmuIXvvIzmnKrpl7vlj4rlvILluLjlkbzlkLjpn7PvvIzmnKrpl7vlj4rlubLmub/llbDpn7PvvIzmnKrpl7vlj4rog7johpzmkanmk6bpn7PvvIzlj4zogrror63pn7PkvKDlr7zmnKrpl7vlj4rmmI7mmL7lvILluLjjgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuW/g+iEjzwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+6KeG6K+K77yaPC9zcGFuPjxzcGFuPuW/g+WJjeWMuuaXoOmahui1t++8jOW/g+WwluaQj+WKqOato+W4uO+8jOS9jeS6juW3puS+p+esrDXogovpl7TplIHpqqjkuK3nur/lhoXkvqcwLjVjbeOAgjwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+6Kem6K+K77yaPC9zcGFuPjxzcGFuPuW/g+WwluaQj+WKqOato+W4uO+8jOS9jee9ruWQjOS4iu+8jOacquinpuWPiumch+mipO+8jOaXoOW/g+WMheaRqeaTpuaEn+OAgjwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+5Y+p6K+K77yaPC9zcGFuPjxzcGFuPuWPjOS+p+a1iumfs+eVjOato+W4uOOAgjwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+5ZCs6K+K77yaPC9zcGFuPjxzcGFuPuW/g+eOhzg15qyhL+WIhu+8jOW/g+W+i+e7neWvueS4jem9kO+8jOesrOS4gOW/g+mfs+W8uuW8seS4jeetie+8jOW/g+WwluWMuuWPr+mXu+WPiumHkeWxnueTo+iGnOmfs++8jFAyPUEy44CC5ZCE55Oj6Iac5ZCs6K+K5Yy65pyq6Ze75Y+K55eF55CG5oCn5p2C6Z+z77yM5pyq6Ze75Y+K6aKd5aSW5b+D6Z+z77yM5pyq6Ze75Y+K5b+D5YyF5pGp5pOm6Z+z44CCPC9zcGFuPjwvcD4NCjxwPjxzcGFuIHN0eWxlPSJmb250LXdlaWdodDpib2xkIj7ooYDnrqE8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuahoeWKqOiEieaQj+WKqDwvc3Bhbj48c3Bhbj4mbmJzcDsmbmJzcDs4MOasoS/liIbvvIzoioLlvovkuI3mlbTpvZDvvIzmnInohInmkI/nn63nu4zvvIzlvLrluqbkuKTkvqflr7nnp7DvvIzml6DlpYfohInjgIHmsLTlhrLohInjgIHkuqTmm7/ohInjgILlkajlm7TooYDnrqHlvoHvvJrml6Dmr5vnu4booYDnrqHmkI/liqjjgIHmnqrlh7vpn7PjgIHliqjohInlvILluLjmkI/liqjjgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuiFuemDqDwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+6KeG6K+K77yaPC9zcGFuPjxzcGFuPuiFuemDqOmahui1t++8jOacquingeiDg+iCoOWei+WPiuigleWKqOazou+8jOiFueWjgemdmeiEieaXoOabsuW8oO+8jOiFueWjgeearuiCpOaXoOearueWue+8jOaXoOiJsue0oOayieedgO+8jOaXoOiFuee6ue+8jOaXoOeWpOeXle+8jOaXoOeWneOAgjwvc3Bhbj48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+DQo8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuinpuiviu+8mjwvc3Bhbj48c3Bhbj7ohbnlo4Hmn5Tova/vvIzohbnogozml6DntKflvKDvvIzml6Dljovnl5vlj4rlj43ot7Pnl5vvvIzmnKrop6blj4rljIXlnZfvvIzogp3ohI/ogovkuIvjgIHliZHnqoHkuIvmnKrop6blj4rvvIzog4blm4rmnKrop6blj4rvvIxNdXJwaHnlvoHpmLTmgKfvvIzohL7ohI/ogovkuIvmnKrop6blj4rvvIzlj4zogr7mnKrop6blj4rvvIzlkITovpPlsL/nrqHngrk8L3NwYW4+PHNwYW4+5pegPC9zcGFuPjxzcGFuPuWOi+eXm++8jOaXoOa2suazoumch+mipOOAgjwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+5Y+p6K+K77yaPC9zcGFuPjxzcGFuPuWRiOm8k+mfs++8jOiCnea1iumfs+eVjOWtmOWcqO+8jOiCneS4iueVjOS9jeS6juWPs+mUgemqqOS4ree6v+esrDXogovpl7TvvIzogp3ljLrml6Dlj6nlh7vnl5vvvIzlj4zkvqfogr7ljLo8L3NwYW4+PHNwYW4+5pegPC9zcGFuPjxzcGFuPuWPqeeXm++8jOenu+WKqOaAp+a1iumfs+mYtOaAp+OAgjwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+5ZCs6K+K77yaPC9zcGFuPjxzcGFuPuiCoOm4o+mfs+ato+W4uO+8jDMtNOasoS/liIbvvIzmnKrpl7vlj4rmsJTov4fmsLTlo7DjgIHooYDnrqHmnYLpn7PjgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuiCm+mXqOOAgeebtOiCoOWSjOWklueUn+auluWZqO+8mjwvc3Bhbj48c3Bhbj7mnKrmo4Dmn6U8L3NwYW4+PHNwYW4+44CCPC9zcGFuPjwvcD4NCjxwPjxzcGFuIHN0eWxlPSJmb250LXdlaWdodDpib2xkIj7ohIrmn7Hlm5vogqLvvJo8L3NwYW4+PHNwYW4+6ISK5p+x55Sf55CG5byv5puy5q2j5bi477yM5peg55W45b2i77yM5rS75Yqo5bqm5q2j5bi477yM5YWo6ISK5p+x5qSO5L2T5peg5Y6L55eb5Y+K5Y+p5Ye755eb44CC5Zub6IKi5Y+K5YWz6IqC5rS75Yqo6Ieq5aaC77yM5peg55W45b2i77yM5YWz6IqC5peg57qi6IK/77yM55qu5rip5q2j5bi477yM5Y+M5L6n5LiL6IKiPC9zcGFuPjxzcGFuPuaXoDwvc3Bhbj48c3Bhbj7msLTogr/vvIzml6DkuIvogqLpnZnohInmm7LlvKDvvIzml6Dlm5vogqLogozogonokI7nvKnvvIzml6DmnbXnirbmjIfjgILogqLkvZPogozlipvjgIHogozlvKDlipvmraPluLjjgII8L3NwYW4+PC9wPg0KPHA+PHNwYW4gc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQiPuelnue7j+ezu+e7nzwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+55Sf55CG5Y+N5bCE77yaPC9zcGFuPjxzcGFuPuinkuiGnOWPjeWwhOOAgeiFueWjgeWPjeWwhOOAgeiCseS6jOWktOiCjOOAgeiCseS4ieWktOiCjOOAgeiGneWPjeWwhOOAgei3n+iFseWPjeWwhOato+W4uOOAgiZuYnNwOzwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+55eF55CG5Y+N5bCE77yaPC9zcGFuPjxzcGFuPuiEkeiGnOWIuua/gOW+geOAgUJhYmluc2tp5b6B44CBSG9mZm1hbm7lvoHpmLTmgKfjgII8L3NwYW4+PC9wPg0KPHAgc3R5bGU9InRleHQtYWxpZ246Y2VudGVyO3RleHQtYWxpZ246Y2VudGVyIj48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+5LiTJm5ic3A7Jm5ic3A756eRJm5ic3A7Jm5ic3A75qOAJm5ic3A7Jm5ic3A75p+lPC9zcGFuPjwvcD4NCjxwPjxzcGFuPiZuYnNwOyZuYnNwOyZuYnNwOyZuYnNwO+W/g+iEj+afpeS9k+WmguS4iui/sOOAgjwvc3Bhbj48L3A+DQo8cCBzdHlsZT0idGV4dC1hbGlnbjpjZW50ZXI7dGV4dC1hbGlnbjpjZW50ZXIiPjxzcGFuIHN0eWxlPSJmb250LXdlaWdodDpib2xkIj7ovoUmbmJzcDsmbmJzcDvliqkmbmJzcDsmbmJzcDvmo4AmbmJzcDsmbmJzcDvmn6U8L3NwYW4+PC9wPg0KPHA+PHNwYW4+Jm5ic3A7Jm5ic3A7Jm5ic3A7Jm5ic3A75bqK6L655b+D55S15Zu+5o+Q56S65b+D5oi/6aKk5Yqo77yMSFImbmJzcDs4NmJwbeOAgjwvc3Bhbj48L3A+DQo8cD48c3BhbiBzdHlsZT0iZm9udC13ZWlnaHQ6Ym9sZCI+DQo8L3NwYW4+PC9wPg0KPHRhYmxlIGNlbGxwYWRkaW5nPSI1IiBib3JkZXI9IjEiIHJ1bGVzPSJhbGwiIHN0eWxlPSJ0ZXh0LWFsaWduOmp1c3RpZnk7IHRleHQtanVzdGlmeTppbnRlci1pZGVvZ3JhcGg7d2lkdGg6MTAwJTtib3JkZXI6MXB0ICMwMDAwMDAgc29saWQgIzAwMDAwMDtib3JkZXItY29sbGFwc2U6Y29sbGFwc2U7ZW1wdHktY2VsbHM6c2hvdzt0YWJsZS1sYXlvdXQ6Zml4ZWQ7Ym9yZGVyLXN0eWxlOnNvbGlkIj48dHI+PHRkPjxwIHN0eWxlPSJmb250LWhlaWdodDogMS43OyI+PHNwYW4+5pyA5ZCO6K+K5patOjwvc3Bhbj48c3Bhbj4xKeaFouaAp+W/g+WKn+iDveS4jeWFqOaApeaAp+WKoOmHjTxiciAvPiAgICAgICAgIDIp5LqM5bCW55Oj54ut56qE5Ly05YWz6Zet5LiN5YWoPGJyIC8+ICAgICAgICAgICAgIOmjjua5v+aAp+W/g+iEj+eTo+iGnOeXhTxiciAvPiAgICAgICAgICAgICDkuozlsJbnk6PmnLrmorDnk6Pnva7mjaLnirbmgIE8YnIgLz4gICAgICAgICAgICAg5oyB57ut5oCn5oi/6aKkPGJyIC8+ICAgICAgICAgMynlhqDlv4Pnl4U8YnIgLz4gICAgICAgICAgICAg5Yag54q25Yqo6ISJ5pSv5p625qSN5YWl5pyv5ZCO54q25oCBPGJyIC8+ICAgICAgICAgNCnpq5jooYDljovnl4Uy57qnKOaegemrmOWNsSk8YnIgLz4gICAgICAgICA1KTLlnovns5blsL/nl4U8L3NwYW4+PHNwYW4+Jm5ic3A7PC9zcGFuPjwvcD48L3RkPjx0ZD48cCBzdHlsZT0iZm9udC1oZWlnaHQ6IDEuNzsiPjxzcGFuPuWFpemZouiviuaWrTo8L3NwYW4+PHNwYW4+MSnpo47mub/mgKflv4PohI/nk6Pohpznl4U8YnIgLz4gICAgICAgICAgICAg5LqM5bCW55Oj54ut56qE5Ly05YWz6Zet5LiN5YWoPGJyIC8+ICAgICAgICAgICAgIOS6jOWwlueTo+acuuaisOeTo+e9ruaNoueKtuaAgTxiciAvPiAgICAgICAgICAgICDmjIHnu63mgKfmiL/poqQ8YnIgLz4gICAgICAgICAgICAg5b+D5Yqf6IO94oWh57qnPGJyIC8+ICAgICAgICAgMinlhqDlv4Pnl4U8YnIgLz4gICAgICAgICAgICAg5Yag54q25Yqo6ISJ5pSv5p625qSN5YWl5pyv5ZCO54q25oCBPGJyIC8+ICAgICAgICAgMynpq5jooYDljovnl4Uy57qnKOaegemrmOWNsSk8YnIgLz4gICAgICAgICA0KTLlnovns5blsL/nl4U8L3NwYW4+PC9wPjwvdGQ+PC90cj48L3RhYmxlPg0KPHA+PHNwYW4+5Yy75biI562+5ZCN77ya5YiY5oWn5bOwL+WtlOWAqeaWhzwvc3Bhbj48c3Bhbj4NCjwvc3Bhbj48c3Bhbj7ljLvluIjnrb7lkI3vvJrliJjmhafls7Av5a2U5YCp5paHPC9zcGFuPjxzcGFuPg0KPC9zcGFuPjxzcGFuPg0KPC9zcGFuPjwvcD48L2Rpdj48ZGl2IGlkPSJmb290ZXIiIHN0eWxlPSJmb250LWZhbWlseTrlrovkvZM7Zm9udC1zaXplOjEwLjVwdDtsaW5lLWhlaWdodDoxLjciPg0KPHAgc3R5bGU9InRleHQtYWxpZ246Y2VudGVyO3RleHQtYWxpZ246Y2VudGVyIj48c3Bhbj48c3Bhbj4gMSA8L3NwYW4+PC9zcGFuPjwvcD48L2Rpdj48L2Rpdj4=\"\n"
                + "}";
        
        String structureJson =
                "{  \"supplementary\": {\n" + "    \"nodeName\": \"辅助检查\",\n" + "    \"parentNode\": \"\",\n"
                        + "    \"endRule\": \"value=医师签名：\"\n" + "  }\n" + "}";
        
        //        String htmlContent = new String(Base64.getDecoder().decode(htmlContentBase64), StandardCharsets.UTF_8);
        
        String htmlContent =
                "<style type=\"text/css\"> .BsoftDefault{font-family:宋体;font-size:10.5pt;line-height:1.7} .BsoftDefault span{font-family:宋体;font-size:10.5pt;line-height:1.7}.BsoftDefault p{font-family:宋体;font-size:10.5pt;line-height:1.7;margin:0px;}</style><div class=\"BsoftDefault\" style=\"width:15.92cm;margin-left:1.54cm\"><div id=\"header\" style=\"font-family:宋体;font-size:10.5pt;line-height:1.7\">\n"
                        + "<p style=\"text-align:center\"><span style=\"font-weight:bold\">\n" + "</span></p>\n"
                        + "<p style=\"line-height:1.0;text-align:center;\"><span style=\"font-size:22pt;font-family:华文行楷\">中山市小榄人民医院</span></p>\n"
                        + "<p style=\"line-height:1.0;text-align:center;\"><span style=\"font-size:15pt;font-family:黑体\">中</span><span style=\"font-family:黑体\">\n"
                        + "</span><span style=\"font-size:15pt;font-family:黑体\">山</span><span style=\"font-family:黑体\">\n"
                        + "</span><span style=\"font-size:15pt;font-family:黑体\">市</span><span style=\"font-family:黑体\">\n"
                        + "</span><span style=\"font-size:15pt;font-family:黑体\">第</span><span style=\"font-family:黑体\">\n"
                        + "</span><span style=\"font-size:15pt;font-family:黑体\">五</span><span style=\"font-family:黑体\">\n"
                        + "</span><span style=\"font-size:15pt;font-family:黑体\">人</span><span style=\"font-family:黑体\">\n"
                        + "</span><span style=\"font-size:15pt;font-family:黑体\">民</span><span style=\"font-family:黑体\">\n"
                        + "</span><span style=\"font-size:15pt;font-family:黑体\">医</span><span style=\"font-family:黑体\">\n"
                        + "</span><span style=\"font-size:15pt;font-family:黑体\">院</span></p>\n"
                        + "<p style=\"line-height:1.5;text-align:center;\"><span style=\"font-size:16pt;font-weight:bold\">入</span><span style=\"font-weight:bold\">\n"
                        + "</span><span style=\"font-size:16pt;font-weight:bold\">院</span><span style=\"font-weight:bold\">\n"
                        + "</span><span style=\"font-size:16pt;font-weight:bold\">记</span><span style=\"font-weight:bold\">\n"
                        + "</span><span style=\"font-size:16pt;font-weight:bold\">录</span></p>\n"
                        + "<p style=\"line-height:0.1000;;text-align:center\"></p>\n"
                        + "<table cellpadding=\"5\" border=\"0\" rules=\"all\" style=\"text-align:justify; text-justify:inter-ideograph;width:100%;border:0pt #000000 solid #000000;border-collapse:collapse;empty-cells:show;table-layout:fixed;border-style:solid\"><tr><td  style=\"width:17.66%;border-right: 0 solid #000000; border-left: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>姓名:</span><span>金雪</span></p></td><td  style=\"width:10.30%;border-right: 0 solid #000000; border-left: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>性别:</span><span>女</span></p></td><td  style=\"width:12.48%;border-right: 0 solid #000000; border-left: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>年龄:</span><span>30岁</span></p></td><td  style=\"width:19.13%;border-right: 0 solid #000000; border-left: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"text-indent:0.12cm\"><span>科室:</span><span>产二科(9楼)</span></p></td><td  style=\"width:17.66%;border-right: 0 solid #000000; border-left: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>床号:</span><span>9-20</span></p></td><td  style=\"width:22.78%;border-right: 0 solid #000000; border-left: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>住院号:</span><span>966132</span></p></td></tr></table>\n"
                        + "<p style=\"line-height:0.1000;;text-align:center\"></p></div><div id=\"main\" style=\"font-family:宋体;font-size:10.5pt;line-height:1.7\">\n"
                        + "<p style=\"line-height:0.1000;\"><span style=\"font-weight:bold\">\n" + "</span></p>\n"
                        + "<table cellpadding=\"5\" border=\"1\" rules=\"all\" style=\"text-align:justify; text-justify:inter-ideograph;width:100%;border:1pt #000000 solid #000000;border-collapse:collapse;empty-cells:show;table-layout:fixed;border-style:solid\"><tr><td  style=\"width:45.90%;border-right: 0 solid #000000; border-left: 0 solid #000000; border-bottom: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>姓名：</span><span>金雪</span></p></td><td  style=\"width:54.10%;border-right: 0 solid #000000; border-left: 0 solid #000000; border-bottom: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>性别：</span><span>女</span></p></td></tr><tr><td  style=\"border-right: 0 solid #000000; border-left: 0 solid #000000; border-bottom: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>年龄：</span><span>30岁</span></p></td><td  style=\"border-right: 0 solid #000000; border-left: 0 solid #000000; border-bottom: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>婚姻：</span><span>已婚</span></p></td></tr><tr><td  style=\"border-right: 0 solid #000000; border-left: 0 solid #000000; border-bottom: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>民族：</span><span>汉族</span></p></td><td  style=\"border-right: 0 solid #000000; border-left: 0 solid #000000; border-bottom: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>职业：</span><span>无业</span></p></td></tr><tr><td  style=\"border-right: 0 solid #000000; border-left: 0 solid #000000; border-bottom: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>出生地：</span><span>湖南省常德市安乡县</span><span>江西</span></p></td><td  style=\"border-right: 0 solid #000000; border-left: 0 solid #000000; border-bottom: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>病史陈述者：</span><span>病人</span></p></td></tr><tr><td  style=\"border-right: 0 solid #000000; border-left: 0 solid #000000; border-bottom: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>入院日期：</span><span>2025-04-01&nbsp;02:30</span></p></td><td  style=\"border-right: 0 solid #000000; border-left: 0 solid #000000; border-bottom: 0 solid #000000; border-top: 0 solid #000000;\"><p style=\"font-height: 1.7;\"><span>记录时间：</span><span>2025-04-01&nbsp;02:34</span></p></td></tr></table>\n"
                        + "<p><span style=\"font-weight:bold\">主诉：</span><span>停经39+2周，下腹胀痛、见红1天。</span></p>\n"
                        + "<p><span style=\"font-weight:bold\">现病史：</span><span>患者平素月经规则，周期30天，经期5天，量中，无痛经，无血块。末次月经2024年06月29日，预产期为2025年04月06日。停经早期无明显恶心、呕吐等早孕反应，孕早期否认流感、风疹病毒感染史，否认接触放射线或有毒物质史，否认腹痛、阴道流血病史，无猫狗密切接触史。停经7+5周在本院建册产检，至今共产检12次，NT彩超、甲功、致畸五项、无创DNA、乙肝丙肝艾滋梅毒、II级彩超、OGTT试验、GBS等无明显异常。停经18+周自觉胎动并活跃至今。孕中晚期无头晕、头痛，无眼花、抽搐，无双下肢水肿。1天前患者无明显诱因出现下腹胀痛，进行性加重，伴见红，无阴道流血，无肛门坠胀感，自觉胎动正常，遂至我院就诊，门诊拟“1、孕1产0孕39+2周、单活胎、头位、产兆”收入院。近期无性生活、外伤史。近期精神、胃纳好，睡眠可，大小便正常。</span></p>\n"
                        + "<p><span style=\"font-weight:bold\">既往史：</span><span>否认高血压、糖尿病、血液病、肝炎、结核等病史；否认手术、外伤史；否认食物、药物过敏史，否认输血史。预防接种史不详。&nbsp;</span></p>\n"
                        + "<p><span style=\"font-weight:bold\">个人史：</span><span>原籍出生、长大。否认到过疫区，无不良嗜好。</span></p>\n"
                        + "<p><span style=\"font-weight:bold\">月经生育史：</span><span>13岁初潮，周期30天，经期5天，末次月经2024-06-29，量中，无血块。无痛经。已婚，孕1产0。丈夫体健，无不良嗜好。</span></p>\n"
                        + "<p><span style=\"font-weight:bold\">家族史：</span><span>否认近亲结婚、遗传病、精神病、痴呆、畸形、双胎、死胎史。</span></p>\n"
                        + "<p style=\"text-align:center;\"><span style=\"font-weight:bold\">体&nbsp;&nbsp;格&nbsp;&nbsp;检&nbsp;&nbsp;查</span></p>\n"
                        + "<p><span>体温37℃，脉搏82次/分，呼吸20次/分，血压124/79（基础压125/71）mmHg。身高160cm，孕前体重58kg，现体重64kg，体重指数25kg/m2。发育正常，营养良好，神志清楚，查体合作。全身皮肤无黄染、苍白。浅表淋巴结无肿大。头部无畸形，五官端正。双侧瞳孔等大同圆，直径约3㎜，对光反射灵敏。结膜无充血，巩膜无黄染。外耳道无异常分泌物，双乳突无压痛。鼻无畸形，鼻腔通畅，鼻窦无压痛。咽峡无充血，扁桃体无肿大。颈无抵抗，气管居中，无颈静脉怒张，甲状腺无肿大。胸廓对称无畸形，呼吸运动不受限；触诊无摩擦感；双肺部叩诊未见异常；呼吸音清晰，未闻及啰音。双乳丰满。心前区无隆起；心尖搏动位于左侧第五肋间锁骨中线内侧1cm处；心界不大；心率82次/分，各瓣膜听诊区未闻及杂音。腹部隆起；肝、脾触诊不满意；双肾区无叩击痛；肠鸣音5次/分。脊柱四肢无畸形，膝腱反射存在。下肢水肿（－），无静脉曲张，无畸形；外阴：无水肿，无静脉曲张；肛门无外痔。</span></p>\n"
                        + "<p style=\"text-align:center;\"><span style=\"font-weight:bold\">专&nbsp;&nbsp;科&nbsp;&nbsp;检&nbsp;&nbsp;查</span></p>\n"
                        + "<p><span>腹形呈纵椭圆，腹围96cm，宫高32cm，胎方位LOA，胎心音150次/分，扪及不规则弱宫缩，先露头，未衔接，跨耻征阴性。肛查：宫颈管消失70%，宫颈位置中，宫颈质中，先露高低S-2，宫口扩张0cm，宫颈Bishop评分：5分。内骨盆：坐骨棘：平；坐骨切迹宽度：容2+指；骶尾关节活动；骶骨弧度：中；骶岬：未扪及。胎膜未破，羊水未查。</span></p>\n"
                        + "<p style=\"text-align:center;\"><span style=\"font-weight:bold\">辅&nbsp;&nbsp;助&nbsp;&nbsp;检&nbsp;&nbsp;查</span></p>\n"
                        + "<p><span>2025年3月21日彩超：宫内单活胎，BPD&nbsp;94mm，AC&nbsp;310mm，FL&nbsp;71mm，AFV&nbsp;54mm，胎盘II级。</span></p>\n"
                        + "<p></p>\n" + "<p><span style=\"font-weight:bold\">\n" + "</span></p>\n"
                        + "<table cellpadding=\"5\" border=\"1\" rules=\"all\" style=\"text-align:justify; text-justify:inter-ideograph;width:100%;border:1pt #000000 solid #000000;border-collapse:collapse;empty-cells:show;table-layout:fixed;border-style:solid\"><tr><td><p style=\"font-height: 1.7;\"><span>最后诊断:</span><span style=\"bgcolor:cae1ca\">1)孕1产1孕39+2周、一活男婴、头位</span><span>&nbsp;</span></p></td><td><p style=\"font-height: 1.7;\"><span>入院诊断:</span><span style=\"bgcolor:cae1ca\">1)孕1产0孕39+2周、单活胎、头位、产兆</span></p></td></tr></table>\n"
                        + "<p><span>医师签名:陈丽华/钟珍琦</span><span>\n" + "</span><span>医师签名:陈丽华/赖思雅</span><span>\n"
                        + "</span></p></div><div id=\"footer\" style=\"font-family:宋体;font-size:10.5pt;line-height:1.7\">\n"
                        + "<p style=\"text-align:center;\"><span><span> 1 </span></span></p></div></div>";
        
        System.out.println(t.docToJson(htmlContent, structureJson));
        ;
    }
    
    public String docToJson(String message, String interactionCode) throws IOException {
        String json = interactionCode;
        Document doc = Jsoup.parse(message);
        Map<String, Map<String, Object>> ruleMap = jsonToMap(json);
        Elements spans = doc.select("span");
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Map<String, List<String>>> parentMap = new HashMap<>();
        ruleMap.forEach((nodeCode, nodeMap) -> {
            List<String> parentList = new ArrayList<>();
            //            Map<String, List<String>> parentMap = new HashMap<>();
            String nodeName = nodeMap.get("nodeName").toString();
            String parentNode = nodeMap.get("parentNode").toString();
            String endRule = nodeMap.get("endRule").toString();
            List<String> valueList = new ArrayList<>();
            if (StringUtils.isNotBlank(endRule)) {
                //value=2、诊断依据：|诊断依据：,label=td|table
                String[] rule = endRule.split(",");
                String key1 = null;
                String value1 = null;
                String key2 = null;
                String value2 = null;
                if (rule.length == 2) {
                    key1 = rule[0].split("=")[0];
                    value1 = rule[0].split("=")[1];
                    key2 = rule[1].split("=")[0];
                    value2 = rule[1].split("=")[1];
                } else {
                    key1 = rule[0].split("=")[0];
                    value1 = rule[0].split("=")[1];
                }
                List<String> resultList1 = new ArrayList<>();
                List<String> resultList2 = new ArrayList<>();
                
                if (Objects.equals(key1, "label")) {
                    resultList1 = endRuleTypeLabel(doc, nodeMap, value1);
                } else {
                    resultList1 = endRuleTypeValue(spans, nodeMap);
                }
                if (StringUtils.isNotBlank(key2)) {
                    if (Objects.equals(key2, "label")) {
                        resultList2 = endRuleTypeLabel(doc, nodeMap, value2);
                    } else {
                        resultList2 = endRuleTypeValue(spans, nodeMap);
                    }
                }
                if (!CollectionUtils.isEmpty(resultList1)) {
                    if (CollectionUtils.isEmpty(resultList2)) {
                        valueList.addAll(resultList1);
                    } else {
                        if (resultList1.size() < resultList2.size()) {
                            valueList.addAll(resultList2);
                        } else {
                            valueList.addAll(resultList1);
                        }
                    }
                }
            }
            if (!CollectionUtils.isEmpty(valueList)) {
                replaceNodeName(valueList, nodeName);
                if (StringUtils.isNotBlank(parentNode)) {
                    Map<String, List<String>> map = new HashMap<>();
                    map.put(parentNode, valueList);
                    parentMap.put(nodeCode, map);
                } else {
                    String value = String.join("", valueList);
                    resultMap.put(nodeCode, value);
                }
            }
        });
        Map<String, List<Map<String, String>>> parentValueMap = handleParentNode(parentMap);
        if (MapUtils.isNotEmpty(parentValueMap)) {
            resultMap.putAll(parentValueMap);
        }
        if (!resultMap.isEmpty()) {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            
            // 将 Map 转换为 JSON 字符串
            System.out.println(objectMapper.writeValueAsString(resultMap));
            return objectMapper.writeValueAsString(resultMap);
        }
        return null;
    }
    
    private static Map<String, Map<String, Object>> jsonToMap(String json) {
        Map<String, Map<String, Object>> result = new HashMap<>();
        try {
            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            // 将 JSON 字符串转换为 Map<String, Map<String, Object>>
            result = objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    private List<String> endRuleTypeLabel(Document doc, Map<String, Object> jsonMap, String ruleValue) {
        List<String> valueList = new ArrayList<>();
        Elements spans = doc.select("span");
        String nodeName = jsonMap.get("nodeName").toString();
        for (Element span : spans) {
            String text = span.text().trim().replaceAll("\\s+", "");
            if (text.contains(nodeName)) {
                Element planP = span.parent();
                valueList.add(text);
                assert planP != null;
                for (Element p : planP.nextElementSiblings()) {
                    if (p.tagName().equals(ruleValue)) {
                        List<String> spanValues = p.select("span").eachText();
                        //                        String spanValue = p.select("span").eachText().stream()
                        //                                .reduce("", (a, b) -> a + b);
                        if (!CollectionUtils.isEmpty(spanValues)) {
                            valueList.addAll(spanValues);
                        }
                    }
                }
            }
        }
        return valueList;
    }
    
    private List<String> endRuleTypeValue(Elements spans, Map<String, Object> jsonMap) {
        // 存储所有nodeName的位置
        List<Integer> nodeNameIndices = new ArrayList<>();
        String nodeName = jsonMap.get("nodeName").toString();
        String endRule = jsonMap.get("endRule").toString().split("=")[1];
        String[] endRuleValue = endRule.split("\\|");
        List<String> valueList = new ArrayList<>();
        for (int j = 0; j < endRuleValue.length; j++) {
            // 存储endRuleValue的位置
            int treatmentPlanIndex = -1;
            // 第一次遍历：记录关键位置
            for (int i = 0; i < spans.size(); i++) {
                String text = spans.get(i).text().trim().replaceAll("\\s+", "");
                if (text.contains(nodeName)) {
                    nodeNameIndices.add(i);
                }
                //                else if (text.contains(endRuleValue[j])) {
                //                    treatmentPlanIndex = i;
                //                    break; // 找到第一个即可
                //                }
                if (text.contains(endRuleValue[j])) {
                    treatmentPlanIndex = i;
                    break;
                }
            }
            // 确定要使用的"鉴别诊断："位置
            int targetNodeIndex = -1;
            for (int i = nodeNameIndices.size() - 1; i >= 0; i--) {
                if (nodeNameIndices.get(i) <= treatmentPlanIndex) {
                    targetNodeIndex = nodeNameIndices.get(i);
                    break;
                }
            }
            // 提取内容
            if (targetNodeIndex != -1 && treatmentPlanIndex != -1) {
                for (int i = targetNodeIndex; i <= treatmentPlanIndex; i++) {
                    if (StringUtils.isNotBlank(spans.get(i).text())) {
                        if (treatmentPlanIndex == targetNodeIndex) {
                            String text = spans.get(i).text();
                            int startIndex = text.indexOf(nodeName) + nodeName.length();
                            int endIndex = text.indexOf(endRuleValue[j]);
                            valueList.add(text.substring(startIndex, endIndex));
                        } else {
                            valueList.add(spans.get(i).text());
                        }
                    }
                }
            }
            if (!CollectionUtils.isEmpty(valueList)) {
                replaceNodeName(valueList, jsonMap.get("nodeName").toString());
                if (!CollectionUtils.isEmpty(valueList)) {
                    break;
                }
            }
        }
        return valueList;
    }
    
    public void replaceNodeName(List<String> valueList, String nodeName) {
        String value = valueList.get(0);
        int i = value.indexOf(nodeName);
        if (i != -1) {
            value = value.substring(i + nodeName.length());
        }
        valueList.set(0, value);
    }
    
    private Map<String, List<Map<String, String>>> handleParentNode(Map<String, Map<String, List<String>>> inputMap) {
        Map<String, List<Map<String, String>>> result = new HashMap<>();
        
        Map<String, Map<String, List<String>>> parentToNodeValues = new HashMap<>();
        
        for (Map.Entry<String, Map<String, List<String>>> nodeEntry : inputMap.entrySet()) {
            String nodeCode = nodeEntry.getKey();
            Map<String, List<String>> parentMap = nodeEntry.getValue();
            
            for (Map.Entry<String, List<String>> parentEntry : parentMap.entrySet()) {
                String parentNodeCode = parentEntry.getKey();
                List<String> values = parentEntry.getValue();
                parentToNodeValues.computeIfAbsent(parentNodeCode, k -> new HashMap<>()).put(nodeCode, values);
            }
        }
        
        // 遍历每个 parentNodeCode，构建 List<Map<nodeCode, value>>
        for (Map.Entry<String, Map<String, List<String>>> parentEntry : parentToNodeValues.entrySet()) {
            String parentNodeCode = parentEntry.getKey();
            Map<String, List<String>> nodeToValues = parentEntry.getValue();
            
            // 计算所有 List<value> 的最大长度
            int maxSize = nodeToValues.values().stream().mapToInt(List::size).max().orElse(0);
            
            // 构建 List<Map<nodeCode, value>>
            List<Map<String, String>> listOfMaps = new ArrayList<>();
            for (int i = 0; i < maxSize; i++) {
                Map<String, String> currentMap = new HashMap<>();
                for (Map.Entry<String, List<String>> nodeEntry : nodeToValues.entrySet()) {
                    String nodeCode = nodeEntry.getKey();
                    List<String> values = nodeEntry.getValue();
                    String value = (i < values.size()) ? values.get(i) : null;
                    currentMap.put(nodeCode, value);
                }
                listOfMaps.add(currentMap);
            }
            
            result.put(parentNodeCode, listOfMaps);
        }
        
        return result;
    }
}
