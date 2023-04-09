package order;

public class OrderData {
        public static Order colourGrey(){
            return new Order("Ким","Кицураги","Ревашоль","3", "+79811039963",  "3","2023-04-17","Срочно, сломал мотокарету", new String[]{"GREY"});
        }

        public static Order colourBlack(){
            return new Order("Ким","Кицураги","Ревашоль","3", "+79811039963",  "3","2023-04-17","Срочно, сломал мотокарету", new String[]{"BLACK"});
        }

        public static Order withoutColour(){
            return new Order("Ким","Кицураги","Ревашоль","3", "+79811039963",  "3","2023-04-17","Срочно, сломал мотокарету", new String[]{"GREY","BLACK"});
        }

        public static Order twoColours(){
            return new Order("Ким","Кицураги","Ревашоль","3", "+79811039963",  "3","2023-04-17","Срочно, сломал мотокарету", null);
        }
    }