package com.rcube.generalknowldgequiz;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final String BOOKMARKS_LIST ="bookmarkslist" ;
    String HISTORY="history";


    public static List<QuestionModal> getQuestionModelList(String category)
    {
        List<QuestionModal> questionModalList=new ArrayList<>();

        //add for sports
	questionModalList.add(new QuestionModal("Which is the winner country of the FIFA World Cup 2018?","Croatia ","France","Brazil","Germany","France","Sports"));
	questionModalList.add(new QuestionModal("What was the official mascot of 13th South Asian Games?","Red Panda ","Black buck","White Deer","Zebra","Black buck","Sports"));
	questionModalList.add(new QuestionModal("Which is the winner country of the ICC world Cup 2019?","Britain","South Africa","New Zealand","Australia","Britain","Sports"));
        questionModalList.add(new QuestionModal("When Was the 13th South Asian Games held in Nepal?","Dec 2-12,2018 ","Dec 3-13,2016","Dec 1-10,2019","Jan 4-13,2019","Dec 1-10,2019","Sports"));
        questionModalList.add(new QuestionModal("Which country will host the 14th South Asian Games in 2021?","Pakistan","India","Sri Lanka","Bhutan","Pakistan","Sports"));
        questionModalList.add(new QuestionModal("Which country won the highest number of medals in the 13th South Asian Games held in Nepal?","Pakistan","Nepal","India","Bangladesh","India","Sports"));
        questionModalList.add(new QuestionModal("Which country won highest number of Gold Medals in 2018 Asian Games?","Japan","Indonesia","China","South Korea","China","Sports"));
        questionModalList.add(new QuestionModal("Which country won the 20-20 world Cup Cricket in 2016?","India","Pakistan","West Indies","Bangladesh","West Indies","Sports"));
        questionModalList.add(new QuestionModal("Best FIFA footballer of year 2018 is ……","Lionel Messi ","Luka Modric ","Ronaldo","Luis Suarez","Luka Modric ","Sports"));
        questionModalList.add(new QuestionModal("Which country won the 20-20 world Cup Cricket in 2016?","India","Pakistan","West Indies","Bangladesh","West Indies","Sports"));
        questionModalList.add(new QuestionModal("What was the highest rank of Nepal in FIFA?","132th","150th","168th","121th","121th","Sports"));
        questionModalList.add(new QuestionModal("Which of the following Country successfully secured the top position in FIFA Ranking-2021?","Germany ","France","Brazil","Belgium","Belgium","Sports"));
        questionModalList.add(new QuestionModal("Who becomes the best FIFA footballer of the year 2019? ","Ronaldo","Lionel Messi ","Luka Modric ","Luis Suarez","Lionel Messi ","Sports"));
        questionModalList.add(new QuestionModal("Who won the Gold Medal in the inaugural event of the 1973 IAAF World Cross Country Championships in women’s event?","Joyce Smith","Rita Ridley","Paola Pigni  ","Julie Brown","Paola Pigni  ","Sports"));
        questionModalList.add(new QuestionModal("Which Indian cricketer has scored the maximum runs in ODIs?","Kapil Dev ","Sunil Gavaskar","Sachin Tendulkar","Virat Kholi","Sachin Tendulkar","Sports"));
        questionModalList.add(new QuestionModal("Ryder cup is related to which sports?","Polo","Golf","Tennis","Badminton","Golf","Sports"));
        questionModalList.add(new QuestionModal("Which Indian singer performed at the Closing Ceremony of the 2018 Asian Games?","AnuMalik","Shaan","SidharthSlathia","Arijit Singh","SidharthSlathia","Sports"));
        questionModalList.add(new QuestionModal("Who is the General Secretary of UEFA?","ReinhardGrindel","TheodoreTheodoridis","FernandoGomes","Michele Uva","TheodoreTheodoridis","Sports"));
	
        questionModalList.add(new QuestionModal("When was the Olympic Council of Asia formed?","1990","1982","1989","1987","1982","Sports"));
        questionModalList.add(new QuestionModal("When was the first UCI Mountain Bike World Cup held?","1989","1990","1991","1992","1989","Sports"));


        //General awereness
	    questionModalList.add(new QuestionModal("Census in Nepal is being held regularly after every ….","6 years","8 years","10 years","12 Years","10 years","General Awareness"));
        questionModalList.add(new QuestionModal("In terms of geographical area Nepal occupies what position in the world?","90th","93th","95th","97th","93th","General Awareness"));
        questionModalList.add(new QuestionModal("Which of the following is the second largest mother language in Nepal?","Maithili","Bhojpuri","Tharu","Kirati","Maithili","General Awareness"));
        questionModalList.add(new QuestionModal("Which cast of Nepal buries with the chicken the cropse in the tomb?","Surel","Hayu","Satar","Hyolmo","Satar","General Awareness"));
        questionModalList.add(new QuestionModal("How many World Heritage sites are in Nepal?","Ten","Nine","Seven","Four","Ten","General Awareness"));
        questionModalList.add(new QuestionModal("“Palam” is the folk song of ","Limbu","Rai","Bahun","Magar","Limbu","General Awareness"));
        questionModalList.add(new QuestionModal("In which dristrict is Tharu museum situated?","Sunsari","Bardiya","Chitwan","Kailali","Chitwan","General Awareness"));
        questionModalList.add(new QuestionModal("Which of the following cast falls under the endangered categories?","Chepang","Raute","Satar","Bankariya","Bankariya","General Awareness"));
        questionModalList.add(new QuestionModal("Which of the following slogon is related with the promotion of visit Nepal Year 2020?","Visit Nepal","Natural Nepal","Lifetime Experiences ","Athiti Devo Bhabha","Lifetime Experiences ","General Awareness"));
        questionModalList.add(new QuestionModal("Energy day in Nepal is observed in ……?","Ashad 2","Bhadra 1","Jestha 9","Ashad 1","Jestha 9","General Awareness"));
        questionModalList.add(new QuestionModal("Chameli Hydropower project is located at ...?","Achham ","Taplejung ","Dolakha ","Darchula","Darchula","General Awareness"));
        questionModalList.add(new QuestionModal("What percentage of population have access on basic drinking water according to economic Survay 076/77 in Nepal?","88%","89%","90%","91%","90%","General Awareness"));
        questionModalList.add(new QuestionModal("What is the target percentage to reduce electricity leakage at the end of FY 2020/2021?","11.3%","13.5% ","12.6%","15%","13.5% ","General Awareness"));
        questionModalList.add(new QuestionModal("When was the use of public vehicles in Nepal Started from?","2016 B.S ","1996 B.S ","2008 B.S ","2013 B.S","1996 B.S ","General Awareness"));
        questionModalList.add(new QuestionModal("When was the name Lukla airport renamed as Tenzing Hillary airport?","2055 B.S ","2064 B.S ","2071BS ","2074BS","2064 B.S ","General Awareness"));
        questionModalList.add(new QuestionModal("When was the first modern census conduct in Nepal?","1920 A.D ","1925 A.D ","1952 A.D ","1966AD","1952 A.D ","General Awareness")); 
        questionModalList.add(new QuestionModal("In which year the population growth rate was highest?","2028 BS","2038 BS","2048 BS","2068 BS","2038 BS","General Awareness"));
        questionModalList.add(new QuestionModal("Which plan in Nepal was observed as agriculture decades?","9th & 10th ","10th &11th ","1st & 2nd ","5th & 6th ","10th &11th ","General Awareness"));
        questionModalList.add(new QuestionModal("How many languages are spoken in Nepal?","123","130 ","129 ","125","129 ","General Awareness"));
        questionModalList.add(new QuestionModal("“Tripitak” is the Holy Book of …..","Siks ","Hindu ","Jews ","Buddhist","Buddhist","General Awareness"));


        //Technology
        questionModalList.add(new QuestionModal("\"www\" stands for:","World Wide Web","World Wide Wares","World Wide Wait","World Wide War","World Wide Web","Technology"));
        questionModalList.add(new QuestionModal("Google (www.google.com) is a","Search Engine","Number in Math","Directory of images","Chat service on the web","Search Engine","Technology"));
        questionModalList.add(new QuestionModal(" What do you call a computer on a network that requests files from another computer?","A client","A host","A router","A web server","A client","Technology"));
        questionModalList.add(new QuestionModal("Hardware devices that are not part of the main computer system and are often added later to the system.","Peripheral","Clip art","Highligh","Execute","Peripheral","Technology"));
        questionModalList.add(new QuestionModal("The main computer that stores the files that can be sent to computers that are networked together is","Clip art","Mother board","Peripheral","File server","File server","Technology"));
        questionModalList.add(new QuestionModal("How can you catch a computer virus?","Sending e-mail messages","Using a laptop during the winter","Opening e-mail attachments","Making computer dusty.","Opening e-mail attachments","Technology"));
        questionModalList.add(new QuestionModal("Which is not an Internet protocol?","HTTP","FTP","STP","IP","STP","Technology"));
        questionModalList.add(new QuestionModal(" Which of the following is not a valid domain name?","www.yahoo.com","www.yahoo.com.co.uk","www.com.yahoo","www.yahoo.org","www.com.yahoo","Technology"));
        questionModalList.add(new QuestionModal("AOL stands for:","Arranged Outer Line","America Over LAN","Audio Over LAN","America On-line","America On-line","Technology"));
        questionModalList.add(new QuestionModal("Another name for a computer chip is:","Execute","Micro chip","Microprocessor","Select","Micro chip","Technology"));
        questionModalList.add(new QuestionModal("World’s largest Quantum Communication line was lunched by ?","Russia","USA","China","Japan","China","Technology"));
        questionModalList.add(new QuestionModal("Which of the following observatory of NASA has discovered Water on a Sunlit Surface of The Moon?","Heli Sphere","Nas Com","SOFIA","Molave","SOFIA","Technology"));
        questionModalList.add(new QuestionModal("Which country lunched world’s first 6G satellite into the space to test 6G technology?","Russia","USA","India","China","China","Technology"));
        questionModalList.add(new QuestionModal("When was digital Signature introduced for the first time in Nepal?","2018","2015","2014","2016","2015","Technology"));
        questionModalList.add(new QuestionModal("When has China launched Chang’e-5 Moon mission an unmanned spacecraft?","Dec 1, 2020","Dec 15, 2020","Dec 7, 2019","Sep 21, 2020","Dec 1, 2020","Technology"));
        questionModalList.add(new QuestionModal("Which city of world becomes the first to have its own Micro-Soft designated font?","Newyork","Banglore","Dubai","Sydney","Dubai","Technology"));
        questionModalList.add(new QuestionModal("In which decade with the first transatlantic radio broadcast occur?","1850s","1860s","1870s","1900s","1900s","Technology"));
        questionModalList.add(new QuestionModal("In which decade was the SPICE simulator introduced?","1950s","1960s","1970s","1980s","1970s","Technology"));
        questionModalList.add(new QuestionModal(" Most modern TV's draw power even if turned off. The circuit the power is used in does what function?","Sound","Remote control","Color balance","High Voltage","Remote control","Technology"));
        questionModalList.add(new QuestionModal("Which is a type of Electrically-Erasable Programmable Read-Only Memory?","Flash","Flange","Fury","FRAM","Flash","Technology"));

        //Current Affairs
        questionModalList.add(new QuestionModal("Who is the current President of Nepal?","Ram Baran Yadav","Baburam Bhatarai","Bidhya Devi Bhandari","Asta Laxmi Shakaya","Bidha Devi Bhandari","Current Affairs"));
        questionModalList.add(new QuestionModal("Who is the current PM of Nepal?","K.P. Sharma Oli","Baburam Bhatarai","Sher Bahadur Deuba","Puspa Kamal Dahal","Sher Bahadur Deuba","Current Affairs"));
        questionModalList.add(new QuestionModal("When was the new Political and Administrative map of Nepal Published?","May, 21, 2020","May, 18, 2020","May, 20, 2020","May, 20, 2020","May, 20, 2020","Current Affairs"));
        questionModalList.add(new QuestionModal("Who is the present Home Minister of Nepal?","Bam Dev Gautam","Ram Bhadur Tapa Badal","Bal Krishna khand","Pumph Bhusal","Bal Krishna khand","Current Affairs"));
        questionModalList.add(new QuestionModal("Which famous city has recently introduced a speed limit of 30 kph as an effort to tackle climate change? NewYork","NewYork","Paris","Rome","Beijing","Paris","Current Affairs"));
        questionModalList.add(new QuestionModal("SAF male football of 2021 going to held on…..","Nepal","India","Bhutan","Maldives","Maldives","Current Affairs"));
        questionModalList.add(new QuestionModal("Who is the present Caption of Nepali women football?","Niru Thapa","Sabitri Bhandari","Namita Rai","Kalpana Ghale","Niru Thapa","Current Affairs"));
        questionModalList.add(new QuestionModal("Who is the present President of USA?","Donald Trump","Joe Biden","Barack Obama","Bill Clinton","Joe Biden","Current Affairs"));
        questionModalList.add(new QuestionModal("President of China Xi Jinping visited Nepal on …..","Oct 2019","Jan 2019","Dec 2019","Nov 2019","Oct 2019","Current Affairs"));
        questionModalList.add(new QuestionModal("When was the first “International Day for People of African Descent” celebrated by the UN?","August,25,2021","August,30,,2021","August 31, 2021","September 1, 2021","August 31, 2021","Current Affairs"));
        questionModalList.add(new QuestionModal("Which country holds the presidency of the Council of the European Union?","Slovenia","Croatia","Germany","Denmark","Slovenia","Current Affairs"));
        questionModalList.add(new QuestionModal("Which is the first Asian country to launch a Plastics pact?","Thailand","Nepal","India","China","India","Current Affairs"));
        questionModalList.add(new QuestionModal("World Social Protection Report 2020-22 is released by which organization?","UNICEF","UNESCO","FAO","ILO","ILO","Current Affairs"));
        questionModalList.add(new QuestionModal("Indian player Manish Narwal, who was in the news recently, is associated with which sport event?","Cricket","Shooting","Hockey","Badminton","Shooting","Current Affairs"));
        questionModalList.add(new QuestionModal("Who is Present Chief Justices of Nepal?","Susila Karki","Cholendra Shamsher Rana","Khila Raj Regmi","Berendra Basnet","Cholendra Shamsher Rana","Current Affairs"));
        questionModalList.add(new QuestionModal("Who is present Chief Minister of Bagmati Province?","Astha Laxmi Sakya","Dormani Poudel","Sher Dhan Rai","Shankar Pokhrel","Astha Laxmi Sakya","Current Affairs"));
        questionModalList.add(new QuestionModal("Who is current Chief of Nepal Army ?","Prabhu Ram Sharma ","Purna Chandra Thapa","Rajandra Chhetri","Pyar Jung Thapa","Prabhu Ram Sharma ","Current Affairs"));
        questionModalList.add(new QuestionModal("Who is present Chief Minister of Province One?","Dormani Poudel","Sher Dhan Rai","Shankar Pokhrel","Bhim Acharya","Bhim Acharya","Current Affairs"));
        questionModalList.add(new QuestionModal("Nepal Government face the Budget holiday for the…..time.","First","Second","Fourth","None of above","First","Current Affairs"));
        questionModalList.add(new QuestionModal("What is the sologan of census 2078?","Koi nachutau","Mero ganana mero sahabhagita .","Ek Ek Gardai Sabai Jana","None of the above","Mero ganana mero sahabhagita .","Current Affairs"));











        List<QuestionModal> questionModalsInGivenCategory=new ArrayList<>();
        for (QuestionModal m:questionModalList
             ) {
            if (m.getCategory().equals(category))
            {
                questionModalsInGivenCategory.add(m);
            }

        }

        return questionModalsInGivenCategory;
    }

}
