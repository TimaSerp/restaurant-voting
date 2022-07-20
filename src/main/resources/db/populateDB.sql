DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO restaurants (name, description, address)
VALUES ('Чайка', 'Птичий ресторан', 'Улица Пушкина, дом Колотушкина'),
       ('Форель', 'Рыбный ресторан', 'Окунева авеню, дом 15'),
       ('Кабанчик', 'Мясной ресторан', 'Свиной проспект, дом 34'),
       ('Без мяса', 'Веганский ресторан', 'Овощной бульвар, дом 0');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100001);

INSERT INTO meals (description, price, restaurant_id)
VALUES ('Цыпленок табака', 500, 100003),
       ('Куриный шашлык', 700, 100003),
       ('Овощи на гриле', 600, 100003),
       ('Компот из сухофруктов', 100, 100004),
       ('Форель запеченная', 500, 100004),
       ('Мясная отбивная', 800, 100005),
       ('Гуляш', 500, 100005),
       ('Ризотто с грибами', 500, 100006),
       ('Смузи из манго', 300, 100006);