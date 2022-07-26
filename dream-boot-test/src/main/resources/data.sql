DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
                                            (1, 'Jone', 18, 'test1@baomidou.com'),
                                            (2, 'Jack', 20, 'test2@baomidou.com'),
                                            (3, 'Tom', 28, 'test3@baomidou.com'),
                                            (4, 'Sandy', 21, 'test4@baomidou.com'),
                                            (5, 'Billie', 24, 'test5@baomidou.com');
DELETE FROM blog;
INSERT INTO blog (user_id, name, publish_time) VALUES
                                            (1, 'java', '2022-07-01'),
                                            (1, 'java核心技术', '2022-07-02'),
                                            (2, 'c++', '2022-07-01'),
                                            (2, 'c++核心技术', '2022-07-02'),
                                            (3, 'python', '2022-07-01'),
                                            (3, 'python核心技术', '2022-07-02'),
                                            (4, 'go', '2022-07-01'),
                                            (4, 'go核心技术', '2022-07-02'),
                                            (5, 'web', '2022-07-01'),
                                            (5, 'web核心技术', '2022-07-02');

