-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts


-- donations

INSERT INTO donations (`id`, email, first_name, last_name, sum, text)
VALUES (1, 'ivan@gmail.com', 'ivan', 'ivanov', 300, 'thank you for your great work!');

INSERT INTO donations (`id`, email, first_name, last_name, sum, phone_number, text)
VALUES (2, 'dragan@gmail.com', 'dragan', 'draganov', 500, '+359888444333', 'awesome job, keep doing it!');

INSERT INTO donations (`id`, email, first_name, last_name, sum, phone_number, text)
VALUES (3, 'georgi@gmail.com', 'georgi', 'georgiev', 200, '+359887666444',
        'thank you for your contribution to the society!');


-- Shelter

INSERT INTO shelter (`id`, name, capacity, description)
VALUES (1, 'Gorni Bogrov', 1000, 'The biggest shelter in Sofia');

-- Medical records

INSERT INTO medical_records (`id`, name)
VALUES (1, 'DESEXED');
INSERT INTO medical_records (`id`, name)
VALUES (2, 'VACCINATED');
INSERT INTO medical_records (`id`, name)
VALUES (3, 'WORMED');
INSERT INTO medical_records (`id`, name)
VALUES (4, 'RABIES');
INSERT INTO medical_records (`id`, name)
VALUES (5, 'MICROCHIPPED');


-- Breed

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('Affenpinscher',
        'The Affenpinscher is square-proportioned, compact and sturdy, with medium bone. This breed is a smaller version of a working terrier, and as such is not a delicate dog. This is an active, tough dog that is agile enough to catch and dispatch rodents. The gait is light, sound, and confident. The Affenpinscher has a monkey-like facial expression with long eyebrows and beard, which lends an air of comic seriousness. The rough coat is about 1 inch long on the body, somewhat longer on the head, neck, chest, stomach and legs. This coat type provided protection from pests and harsh conditions.',
        'SMALL',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637161985/adoptDontShop/Breeds/affenpinscher-detail_snthpf.jpg',
        80,
        50, 80, 95, 10, 90, 60, 70);

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('Beagle',
        'The Beagle should look like a miniature Foxhound, and is solid for the size. The Beagle’s moderate size enables the ability to follow on foot. Beagles can also be carried, and they can scurry around in thick underbrush. Their close hard coat protects them from underbrush. Their moderate build enables them to nimbly traverse rough terrain. The Beagle’s amiable personality allows this breed to get along with other dogs and to be a wonderful pet. Beagles are noted for their melodious bay. The deep muzzle allows more room for olfactory receptors, aiding the Beagle’s uncanny sense of smell.',
        'SMALL',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637161967/adoptDontShop/Breeds/beagle-detail_fb5cuw.jpg',
        100, 10, 100, 10, 19, 95, 100, 100);

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('Boston Terrier',
        'The Boston Terrier is a compactly built, square-proportioned, short-backed, clean-cut dog. This breed conveys the impression of determination, strength, sturdiness, liveliness, and style, with a graceful carriage. The Boston retains many of the attributes of his Bulldog ancestors, but in a clean-cut package that makes a handy house companion. The short fine coat, with distinctive markings, adds to this breed’s dapper appearance.',
        'SMALL',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637162290/adoptDontShop/Breeds/boston-terrier-detail_ejhbqx.jpg',
        60, 60, 60, 10, 10, 65, 65, 90);

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('Cavalier',
        'The Cavalier King Charles Spaniel is an elegant, royal, toy spaniel, slightly longer than tall, with moderate bones. The Cav retains the build of a working spaniel, yet in a smaller version. Their gait is free and elegant, with good reach and drive. Their silky coat is of moderate length, with a slight wave permissible. Long feathering on the feet is a breed characteristic. A hallmark of the breed is its gentle, sweet, melting expression.',
        'SMALL',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637162431/adoptDontShop/Breeds/cavalier-king-charles-spaniel-detail_gqagos.jpg',
        100, 90, 60, 65, 9, 86, 100, 100);

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('Mastiff',
        'The massive Mastiff is heavy boned with a powerful musculature, being slightly longer than tall. Great strength is combined with endurance. Power and strength are evident in this dog’s gait, which has good reach and drive. The double coat consists of a dense undercoat and a straight, coarse, outer coat of moderately short length. The expression is alert but kindly. The overall impression is one of grandeur and dignity.',
        'LARGE',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637162631/adoptDontShop/Breeds/mastiff-detail_fcahux.jpg',
        70, 62, 10, 11, 90, 10, 32, 81);

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('Labrador Retriever ',
        'The Labrador is a moderate dog, not extreme in any way. It is square or slightly longer than tall, of fairly large bone and substance. The breed’s broad head and strong jaws enabled the dog to carry the largest game birds, such as Canada geese. A heavy body and strong legs enable the dog to swim and run powerfully. The coat, which is short, straight, and dense with a soft undercoat, is weatherproof and helps to protect it from icy waters. The Lab is a working retriever and possesses style without over-refinement, and substance without clumsiness.',
        'LARGE',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637163067/adoptDontShop/Breeds/Labrador-482x260_bovs9g.png',
        95, 100, 80, 24, 21, 100, 100, 100);

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('Rottweiler',
        'The Rottweiler is a medium large breed, slightly longer than they are tall, and robust with a powerful, substantial build. Historically, they combine the abilities that were necessary for jobs that entail great strength, agility, and endurance. Their trot is sure and powerful, with strong reach and drive. Their coat is straight, coarse, and dense. Their muzzle is short enough for strength but long enough for proper breathing and cooling. Their expression reflects some of their best traits; noble, alert, and self-assured.',
        'LARGE',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637162766/adoptDontShop/Breeds/rottweiler-detail_nolnhn.jpg',
        21, 61, 31, 5, 100, 26, 21, 21);

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('Golden Retriever',
        'Goldens are athletic, strong and capable of carrying heavy game over land and water, using a broad, powerful head with strong neck and well-developed fore and hindquarters. The breed is slightly longer than tall, with a smooth, powerful, ground-covering gait, and dense, straight or wavy outer water-repellent coat.',
        'LARGE',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637162858/adoptDontShop/Breeds/golden-retriever-detail_xuifsg.jpg',
        100, 100, 55, 67, 23, 54, 100, 100);

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('Boykin Spaniel',
        'The Boykin Spaniel is a medium-sized dog built to flush and retrieve over all types of ground conditions with agility and reasonable speed. This breed has strong, but not overly heavy, bone and is slightly longer than tall. The jaws are long and strong. The coat has a medium length outer coat that can range from flat to slightly wavy, which protects from the elements and repels water; and a short, dense undercoat for insulation. Movement is effortless with good reach and strong drive.',
        'MEDIUM',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637163392/adoptDontShop/Breeds/boykin-spaniel-detail_gju8jm.jpg',
        87, 82, 83, 62, 13, 79, 100, 81);

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('Border Collie',
        'This is a medium dog of strong bone, slightly longer than tall, combining grace, agility, substance, and stamina. The Border Collie’s trot is smooth, ground covering, and tireless, moving with stealth and strength. They are able to change speed and direction suddenly. This breed can display incredible agility even after working for long periods. The coat can be either smooth or rough. The smooth coat is short all over the body; the rough coat is medium to long in length and flat to slightly wavy in texture. The BC’s expression is intelligent, alert, eager, and full of interest, a reflection of its temperament.',
        'MEDIUM',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637163628/adoptDontShop/Breeds/border-collie-detail_yje5eg.jpg',
        62, 100, 100, 54, 62, 92, 30, 12);

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('English Cocker Spaniel',
        'The English Cocker was bred to find, flush, and retrieve upland game birds during a full day of hunting. The dog is slightly taller than long, compactly built, and short coupled. The English Cocker has a driving, powerful gait that covers ground effortlessly. The coat is of medium length, silky in texture, and either flat or slightly wavy. The feathering should not be so profuse that it becomes a hindrance in the field, but it should be long enough to protect the underside of the dog. The expression is soft and melting, yet dignified.',
        'MEDIUM',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637163892/adoptDontShop/Breeds/english-cocker-spaniel-detail_prq4jr.jpg',
        100, 85, 52, 68, 35, 78, 100, 100);

INSERT INTO breeds(name, description, size, image_url, affection_level, ease_of_training, energy_level,
                   grooming_requirements,
                   watchfulness, playfulness, friendliness_to_strangers, friendliness_to_other_pets)
VALUES ('Dalmatian',
        'The Dalmatian is a square-proportioned, athletic dog of good substance and sturdy bone. They are built for efficiency at the trot and great endurance, and their movement should be steady and effortless. The expression is alert and intelligent; the coat short and sleek.',
        'MEDIUM',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637163738/adoptDontShop/Breeds/dalmatian-detail_hkuzo5.jpg',
        100, 40, 85, 28, 62, 68, 100, 67);

-- Dogs

-- Dog - Frances
INSERT INTO dogs(id, name, gender, age, colour, coat_length, weight, description, medical_notes, size, house_trained,
                 get_along_with,
                 added_on,
                 is_adopted, user_entity_id, breed_id, shelter_id)
VALUES (1, 'Frances', 'FEMALE', 14, 'Brown', 'MEDIUM', 12,
        'Frances is a very gentle affectionate dog and she is playful and confident with children. She suits 5+yo kids. She is calm and easy to handle and walks on loose lead. She came from the pound with an injured tail from wagging it too much and hitting the kennel wall. Her tail is very long but is slowly healing.',
        'She comes desexed, C5 vaccinated, heartworm free and microchipped. Also included for the love and health of our dogs is a free Health and Wellness Voucher with our DR Vet.',
        'MEDIUM', true, 'kids, dogs and other people', now(), false, null, 11, 1);

INSERT INTO pictures(id, title, url, dog_id)
VALUES (1, 'Frances',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637167528/adoptDontShop/Dogs/Frances/frances-3_daps2v.png',
        1);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (2, 'Frances',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637167528/adoptDontShop/Dogs/Frances/frances-2_cbasob.jpg',
        1);

INSERT INTO pictures(id, title, url, dog_id)
VALUES (3, 'Frances',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637167528/adoptDontShop/Dogs/Frances/frances-4_rlk8wx.jpg',
        1);

INSERT INTO pictures(id, title, url, dog_id)
VALUES (4, 'Frances',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637167527/adoptDontShop/Dogs/Frances/frances-1_exjbwy.jpg',
        1);

-- Dog - Benji
INSERT INTO dogs(id, name, gender, age, colour, coat_length, weight, description, medical_notes, size, house_trained,
                 get_along_with,
                 added_on,
                 is_adopted, user_entity_id, breed_id, shelter_id)
VALUES (2, 'Benji', 'MALE', 42, 'Brown', 'MEDIUM', 33,
        'Benji needs time to trust you. He is not so high energy and not jumpy like many at his age but he is strong. He is often hesitant to walk. He ignored cars but lunges at some passing dogs. He much prefers walking on bush tracks rather than roadways. Volunteers say “Beautiful happy big boy. Very dog reactive but very friendly with humans. Loved his splash pool.” "A but reluctant to walk at first. Once over the hill he walked so well on loose lead. Wonderful temperament. Super friendly and affectionate" He can be a bit sensitive about having his legs touched. He has a thicker lab like coat and weighs around 33kg.',
        'He comes desexed, C5 vaccinated, heartworm free and microchipped. Also included for the love and health of our dogs is a free Health and Wellness Voucher with our DR Vet.',
        'LARGE', false, 'kids, but afraid of stranger people', now(), false, null, 5, 1);

INSERT INTO pictures(id, title, url, dog_id)
VALUES (5, 'Benji',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637170240/adoptDontShop/Dogs/Benji/Benji-1_sarlph.jpg',
        2);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (6, 'Benji',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637170240/adoptDontShop/Dogs/Benji/Benji-3_c4selp.jpg',
        2);

INSERT INTO pictures(id, title, url, dog_id)
VALUES (7, 'Benji',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637170240/adoptDontShop/Dogs/Benji/Benji-4_mxubse.jpg',
        2);

INSERT INTO pictures(id, title, url, dog_id)
VALUES (8, 'Benji',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637170240/adoptDontShop/Dogs/Benji/Benji-2_ezfdzg.jpg',
        2);

-- Dog - Banjo

INSERT INTO dogs(id, name, gender, age, colour, coat_length, weight, description, medical_notes, size, house_trained,
                 get_along_with,
                 added_on,
                 is_adopted, user_entity_id, breed_id, shelter_id)
VALUES (3, 'Banjo', 'MALE', 21, 'White and Brown', 'SHORT', 13,
        'Banjo is a sensitive highly anxious boy who needs a lot of reassurance from an experienced calm and confident adult. He is very loving to people he trusts. Banjo will growl or snap if he feels threatened or cornered. He is groomed under sedation or with a muzzle as he is sensitive to touch of parts of face and hind legs. He is social with other small dogs. He has a non-shedding coat and weighs 6.6kg.',
        'He comes desexed, C5 vaccinated, heartworm free and microchipped. Also included for the love and health of our dogs is a free Health and Wellness Voucher with our DR Vet',
        'SMALL', true, 'kids, other dogs and strangers', now(), false, null, 2, 1);


INSERT INTO pictures(id, title, url, dog_id)
VALUES (9, 'Banjo',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637171402/adoptDontShop/Dogs/Banjo/Banjo-2_dpmisb.jpg',
        3);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (10, 'Banjo',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637171402/adoptDontShop/Dogs/Banjo/Banjo_xokx3a.jpg',
        3);

INSERT INTO pictures(id, title, url, dog_id)
VALUES (11, 'Banjo',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637171402/adoptDontShop/Dogs/Banjo/Banjo-3_oyk9ab.jpg',
        3);

-- Dog - Boo

INSERT INTO dogs(id, name, gender, age, colour, coat_length, weight, description, medical_notes, size, house_trained,
                 get_along_with,
                 added_on,
                 is_adopted, user_entity_id, breed_id, shelter_id)
VALUES (4, 'Boo', 'MALE', 26, 'Brown and White', 'SHORT', 16,
        'Hi I’m Boo🐾
I am a 2 year old Beagle looking for a new home! My old mum and dad can’t keep me at their place anymore cause I’ve decided to start wandering too far from home so I need a new home with a secure back yard!
If you can’t tell from the picture my favourite thing in the whole world is to play fetch with my ball, or any ball, even a half chewed ball! So I need a new friend who will throw it for me everyday! I also love going on adventures to the river and splashing about in the water! I have been camping a few time but need to stay on a long lead cause I like to explore a bit further than I’m allowed. I don’t like car rides but will put up with them if it means going on an adventure, just might need a little gentle encouragement to get back in! I sit good on the back of a ute but again I don’t really like it, so treats work well to calm me down.
I currently live with 3 other pups who don’t seem to like me very much but I’m not interested in them, all I care about is my ball or rope and someone to play with! So maybe a new home where I can be the only dog and centre of attention would be good! I have been around cats and even though they’re a bit strange I don’t mind them at all, can’t focus on them too much when I can focus on playing with my ball instead!
My old mum and dad love me lots but just can’t give me what I need right now but will be very picky on my next home! A new young couple or family that has the time and patience to take me on should definitely submit an application! Can’t wait to meet you!',
        'She comes desexed, C5 vaccinated, heartworm free and microchipped. Also included for the love and health of our dogs is a free Health and Wellness Voucher with our DR Vet',
        'SMALL', true, 'kids, other dogs and strangers', now(), false, null, 2, 1);


INSERT INTO pictures(id, title, url, dog_id)
VALUES (12, 'Boo',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637203952/adoptDontShop/Dogs/Boo/Boo-3_qvelok.jpg',
        4);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (13, 'Boo',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637203952/adoptDontShop/Dogs/Boo/Boo-4_se5lmc.jpg',
        4);

INSERT INTO pictures(id, title, url, dog_id)
VALUES (14, 'Boo',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637203952/adoptDontShop/Dogs/Boo/Boo_esr7ok.jpg',
        4);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (15, 'Boo',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637203952/adoptDontShop/Dogs/Boo/Boo-2_pj5fwz.jpg',
        4);

-- Dog - James

INSERT INTO dogs(id, name, gender, age, colour, coat_length, weight, description, medical_notes, size, house_trained,
                 get_along_with,
                 added_on,
                 is_adopted, user_entity_id, breed_id, shelter_id)
VALUES (5, 'James', 'MALE', 8, 'Black and White', 'MEDIUM', 11,
        'James is a 9 week old beautiful Border collie boy pup who is now available for adoption. James and his two brothers have been together from the beginning and love each other’s company so much, they are very sweet natured loving boys that are intelligent so should be easy to train. They are responding to their names, know how to come and sit already. They have typical staffy characteristics with energy, playfulness and love to have company most of the time, they are all only going to be medium sized dogs and have slim builds.
James is a happy little chap but also likes his quiet time more than his brothers, he gets on well with other dogs and is use to both inside and outside environments in which to run and play in so will need access to both of these with his new home.
James loves company and loves being around other dogs which is why we will be looking for a forever home with another medium sized companion dog for him to go with and a family that has some spare time during the week as he is still a pup and needs extra attention and training. He enjoys games like tug of war and chase with his foster dogs but will quite happily have a snuggle or play in the yard with all his toys.
He loves his outside environment so please NO apartments or courtyard applications as he will be a medium/big dog (but not large) so will need plenty of space. ',
        'As James is not yet desexed and vaccinated, he is only available for adoption to Sofia city, because he will need to be returned to a local vet for desexing when he is approximately 5/6 months old. Please NO out of area applications.',
        'MEDIUM', true, 'kids, other dogs and strangers', now(), false, null, 10, 1);


INSERT INTO pictures(id, title, url, dog_id)
VALUES (16, 'James',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637205271/adoptDontShop/Dogs/Max/James_uiymap.jpg',
        5);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (17, 'James',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637205271/adoptDontShop/Dogs/Max/James-3_kup1gy.jpg',
        5);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (18, 'James',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637205271/adoptDontShop/Dogs/Max/James-4_vbyqjk.jpg',
        5);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (19, 'James',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637205271/adoptDontShop/Dogs/Max/James-2_x3mqx1.jpg',
        5);

-- Dog - Rose

INSERT INTO dogs(id, name, gender, age, colour, coat_length, weight, description, medical_notes, size, house_trained,
                 get_along_with,
                 added_on,
                 is_adopted, user_entity_id, breed_id, shelter_id)
VALUES (6, 'Rose', 'FEMALE', 35, 'Black', 'MEDIUM', 36,
        'Meet Rose - the very sweet adult Rottweiler. She’s a very loving little guy who is extremely social once he comes out of his shell.
Rose is very friendly towards people, little kids, cats and all other dogs. Anyone he comes into contact with loves him. She has bonded really well with his foster brother and his favourite thing to do is play, so Freddie would be happiest with a friendly puppy pal to rough and tumble with.
Rose is very clever and a quick learner. Since coming into care a month ago he has learnt to sit for food and to go outside to the toilet. Accidents still happen but he is just a puppy.
Rose is eager to learn and please you so will come when called every time and will do anything for a treat. He loves food! Use this to deter him from typical puppy behavior like stealing shoes. Big juicy raw bones and other healthy long lasting chew food or enrichment toys will keep his teething and puppy mind occupied.
Rose will need a big yard with high fences & an active family to keep up with what will be a big high energy boy. He will need more training on the lead & continued encouragement for outside toileting. The extra effort now in his puppy months will be worth it with this cheeky boy who loves any attention he can get.',
        'Rose is vaccinated, microchipped and will need to be returned to our DRN approved vet for desexing. For this reason she is not available to our of area adoptions',
        'LARGE', true, 'kids and dogs', now(), false, null, 7, 1);


INSERT INTO pictures(id, title, url, dog_id)
VALUES (20, 'Rose',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637205560/adoptDontShop/Dogs/Rose/Rose-2_qaphcn.jpg',
        6);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (21, 'Rose',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637205560/adoptDontShop/Dogs/Rose/Rose-4_fygxp9.jpg',
        6);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (22, 'Rose',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637205560/adoptDontShop/Dogs/Rose/Rose-3_uk7odw.jpg',
        6);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (23, 'Rose',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637205560/adoptDontShop/Dogs/Rose/Rose_cikos4.jpg',
        6);

-- Dog - Daisy

INSERT INTO dogs(id, name, gender, age, colour, coat_length, weight, description, medical_notes, size, house_trained,
                 get_along_with,
                 added_on,
                 is_adopted,  user_entity_id, breed_id, shelter_id)
VALUES (7, 'Daisy', 'FEMALE', 40, 'White with black dots', 'MEDIUM', 24,
        'Daisy is a mature lady, who still has a few puppy-like behaviours. She still finds life super interesting and has plenty of energy, life and love to share in her new home. She still enjoys a daily walk and loves a game with a tennis ball. Layla enjoys car rides and is toilet trained. Layla is an outgoing lady who enjoys making friends with other dogs and new people!
We recommend Daisy for a home with children aged 8 years and older. Please remember that all interactions between children and dogs should be actively monitored.',
        'Daisy is vaccinated, microchipped and desexed. Recently she got wormed and tested for rabies. For this reason she is available to our of Sofia-city adoptions',
        'MEDIUM', true, 'kids,dogs and strangers', now(), false, null, 12, 1);


INSERT INTO pictures(id, title, url, dog_id)
VALUES (24, 'Daisy',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637206408/adoptDontShop/Dogs/Daisy/Daisy-4_htxwbk.jpg',
        7);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (25, 'Daisy',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637206408/adoptDontShop/Dogs/Daisy/Daisy-2_pkz2w4.jpg',
        7);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (26, 'Daisy',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637206408/adoptDontShop/Dogs/Daisy/Daisy_sfog0n.jpg',
        7);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (27, 'Daisy',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637206408/adoptDontShop/Dogs/Daisy/Daisy-3_p4lhbm.jpg',
        7);

-- Dog - Mikka

INSERT INTO dogs(id, name, gender, age, colour, coat_length, weight, description, medical_notes, size, house_trained,
                 get_along_with,
                 added_on,
                 is_adopted, user_entity_id, breed_id, shelter_id)
VALUES (8, 'Mikka', 'FEMALE', 5, 'White and Brown', 'LONG', 3,
        'Mikka is an affectionate girl once she trusts you. She is not a fan of being picked up but happy to be led. She is a sweet cuddly girl who loves belly rubs.
She is best as the only dog in the household. Mikka came with a tumour on her mouth and 2 teeth embedded in her lower jaw which have all been removed. Our specialist said it was a very low grade slow growing tumour which should not present her with major issues should it re-occur.
She is prone to ear infections in hot months and she needs to be muzzled for ear drops/ cleaning and also for clipping.
She would suit a person with vet nursing experience. She has a low shedding coat and weighs 6.2kg.
Suit Home Based Worker: Unable to cope alone and has shown signs of separation anxiety. Needs human company.
Apartment Friendly: Could cope in an apartment environment. Regular walks or use of an artificial grass patch is suggested.',
        'She comes desexed, C5 vaccinated, heartworm free and microchipped. Also included for the love and health of our dogs is a free Health and Wellness Voucher with our DR Vet.',
        'SMALL', false, 'kids,dogs and strangers', now(), false, null, 4, 1);

INSERT INTO pictures(id, title, url, dog_id)
VALUES (28, 'Mikka',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637206997/adoptDontShop/Dogs/Mikka/Mikka-2_voimrb.jpg',
        8);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (29, 'Mikka',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637206997/adoptDontShop/Dogs/Mikka/Mikka_dttbmq.jpg',
        8);
INSERT INTO pictures(id, title, url, dog_id)
VALUES (30, 'Mikka',
        'https://res.cloudinary.com/yavorhr/image/upload/v1637206997/adoptDontShop/Dogs/Mikka/Mikka-3_ywlzvn.jpg',
        8);

INSERT INTO dogs_medical_record VALUES (1,1);
INSERT INTO dogs_medical_record VALUES (1,2);
INSERT INTO dogs_medical_record VALUES (1,3);
INSERT INTO dogs_medical_record VALUES (1,4);
INSERT INTO dogs_medical_record VALUES (1,5);

INSERT INTO dogs_medical_record VALUES (2,1);
INSERT INTO dogs_medical_record VALUES (2,2);
INSERT INTO dogs_medical_record VALUES (2,3);
INSERT INTO dogs_medical_record VALUES (2,4);

INSERT INTO dogs_medical_record VALUES (3,1);
INSERT INTO dogs_medical_record VALUES (3,2);
INSERT INTO dogs_medical_record VALUES (3,3);
INSERT INTO dogs_medical_record VALUES (3,4);
INSERT INTO dogs_medical_record VALUES (3,5);

INSERT INTO dogs_medical_record VALUES (4,1);
INSERT INTO dogs_medical_record VALUES (4,2);
INSERT INTO dogs_medical_record VALUES (4,3);
INSERT INTO dogs_medical_record VALUES (4,4);
INSERT INTO dogs_medical_record VALUES (4,5);

INSERT INTO dogs_medical_record VALUES (5,2);
INSERT INTO dogs_medical_record VALUES (5,3);
INSERT INTO dogs_medical_record VALUES (5,4);
INSERT INTO dogs_medical_record VALUES (5,5);

INSERT INTO dogs_medical_record VALUES (6,3);
INSERT INTO dogs_medical_record VALUES (6,4);
INSERT INTO dogs_medical_record VALUES (6,5);
INSERT INTO dogs_medical_record VALUES (6,1);

INSERT INTO dogs_medical_record VALUES (7,3);
INSERT INTO dogs_medical_record VALUES (7,4);
INSERT INTO dogs_medical_record VALUES (7,5);
INSERT INTO dogs_medical_record VALUES (7,1);
INSERT INTO dogs_medical_record VALUES (7,2);

INSERT INTO dogs_medical_record VALUES (8,3);
INSERT INTO dogs_medical_record VALUES (8,4);
INSERT INTO dogs_medical_record VALUES (8,5);
INSERT INTO dogs_medical_record VALUES (8,1);
INSERT INTO dogs_medical_record VALUES (8,2);