package beans;

import db.ProductDb;
import entity.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import util.BinFileUtil;

@Stateless
public class InitProducts {

    @EJB
    ProductDb db;
    String[] name = {"Ladies KC Heart CapB2:C17", "Girls KC Sparkle Cap", "KC Field Cap", "KC Midnight Cap", 
                     "Childs KC Adjustable Cap", "Childs Fitted KC Cap", "KC Sunday Cap", "KC 50th Season Cap", 
                     "KC 2015 WS Cap", "Nihon Golf Cap", "O-H-I-O Cap", "Toilet Browns Cap", 
                     "Boomer OU Cap", "KC Season Ticket Cap", "Northern Neighbors Cap", "Bye Bye Smith Cap", 
                     "Norman Football Cap", "Pew Pew Cap", "Miners Cap", "KC Retro Cap", 
                     "In Wheat We Trust Cap", "Mayfield to the Rescue Cap", "Its Not Summer Stocking", "Were talking about Playoffs"};
    
    Double[] price = {14.99, 12.99, 24.99, 19.99,
            4.99, 9.99, 12.99, 50.00, 99.99, 124.99, 
            35.99, 0.09, 29.99, 85.15, 22.99, 39.99, 34.99, 79.99, 
            124.99, 67.99, 35.00, 69.69, 14.99, 15.15 };
    
    AppKind[] kind = {AppKind.KIND1, AppKind.KIND1, AppKind.KIND2, AppKind.KIND3, 
        AppKind.KIND2, AppKind.KIND3, AppKind.KIND3, AppKind.KIND3, 
        AppKind.KIND2, AppKind.KIND1, AppKind.KIND2, AppKind.KIND3, 
        AppKind.KIND3, AppKind.KIND3, AppKind.KIND1, AppKind.KIND2, 
        AppKind.KIND1, AppKind.KIND1, AppKind.KIND3, AppKind.KIND2, 
        AppKind.KIND1, AppKind.KIND1, AppKind.KIND1, AppKind.KIND1};
    
    String[] text = {
        "All ladies love the local Baseball team.  This cap lets you express that love with a diamond heart around the city we all love.",
        "Bedazzled lovers will enjoy routing on this had with multi sparkles sprinkles around the KC logo.",
        "Just like what the professionals where on the field.  Cap does not come with multi-million dollar paychecks.",
        "Jet black cap with the blue KC logo.  Its so stealthy it’s issued to all Ninja B-2 pilots.",
        "Like daddy like son, but this hat will grown with your child.",
        "All kids love to see that batter symbol on the back of the fitted cap.  This one has it!",
        "Don't let this sun ruin your day game.  This light colored hat keeps your head cool while your team burns up on the field.",
        "Celebrate the 50th win in the 50th season with this hat.  Even comes with a golden ticket on the brim.",
        "The good ole days are behind us and this hat will serve as a keepsake of the golden days.",
        "Golf like a Japanese Pro.  Wearing this hat will get you into any golf course in Japan.",
        "Route on your team with this hat.  It does not come with championship rings or any other player memorabilia.",
        "Things can only go up when you wear this hat.  Don’t miss the win by not wearing this hat on Game Day.",
        "Big Game Bob is gone, but can bring you some game day luck.",
        "Be one of the few with this customized hat that shows your pride and perseverance.",
        "Ready to move up North? This cap will make you fit in like maple syrup in a 55 gallon barrel.",
        "Rocket into the future with this hat.  This hat will protect your head when the scoreboard explodes due to the high scoring games.",
        "Classic sideline hat for any Sooners fan.  Just make sure you sneak into the stadium early to claim your land!",
        "Nothing can stop this hat.  It just keeps going and going.",
        "So cool, even an Engineer would wear this green and silver hat.  It even comes with your very own pick axe.",
        "Don’t like Green and Gold, then go with this retro A's cap.",
        "Who doesn’t like a sell out?  Support the local crew with this hat.",
        "Who is ready for a win?  Then slap on this cap and plant your flag as a win is guaranteed.",
        "If you want to feel like you are in The Christmas Story, then pick up this stocking cap.  Just don’t lick any poles.",
        "Has your team made a deep run into the playoffs, then grab this stocking cap on your way to the Championship!",
    };
    
        

    String[] urlS = {"/resources/images/S01.jpg", "/resources/images/S02.jpg", "/resources/images/S03.jpg", "/resources/images/S04.jpg", "/resources/images/S05.jpg",
        "/resources/images/S06.jpg", "/resources/images/S07.jpg", "/resources/images/S08.jpg", "/resources/images/S09.jpg", "/resources/images/S10.jpg",
        "/resources/images/S11.jpg", "/resources/images/S12.jpg", "/resources/images/S13.jpg", "/resources/images/S14.jpg", "/resources/images/S15.jpg",
        "/resources/images/S16.jpg", "/resources/images/S17.jpg", "/resources/images/S18.jpg", "/resources/images/S19.jpg", "/resources/images/S20.jpg",
        "/resources/images/S21.jpg", "/resources/images/S22.jpg", "/resources/images/S23.jpg", "/resources/images/S24.jpg",};

    String[] urlL = {"/resources/images/L01.jpg", "/resources/images/L02.jpg", "/resources/images/L03.jpg", "/resources/images/L04.jpg", "/resources/images/L05.jpg",
        "/resources/images/L06.jpg", "/resources/images/L07.jpg", "/resources/images/L08.jpg", "/resources/images/L09.jpg", "/resources/images/L10.jpg",
        "/resources/images/L11.jpg", "/resources/images/L12.jpg", "/resources/images/L13.jpg", "/resources/images/L14.jpg", "/resources/images/L15.jpg",
        "/resources/images/L16.jpg", "/resources/images/L17.jpg", "/resources/images/L18.jpg", "/resources/images/L19.jpg", "/resources/images/L20.jpg",
        "/resources/images/L21.jpg", "/resources/images/L22.jpg", "/resources/images/L23.jpg", "/resources/images/L24.jpg",};

    public void init() {
        for (int i = 0; i < name.length; i++) {
            byte[] pic1 = BinFileUtil.getBinary(urlS[i]);
            byte[] pic2 = BinFileUtil.getBinary(urlL[i]);
            Product product = new Product(name[i], price[i], kind[i], text[i], pic1, pic2);
            db.add(product);
        }

    }
}
