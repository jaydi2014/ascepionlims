// UDMv4.44 //
///////////////////////////////////////////////////////////////////
var um={'menuClasses':[],'itemClasses':[],'menuCode':[]};
///////////////////////////////////////////////////////////////////
//                                                               //
//  ULTIMATE DROP DOWN MENU Version 4.44 by Brothercake          //
//  http://www.udm4.com/                                         //
//                                                               //
//  This script may not be used or distributed without license   //
//                                                               //
///////////////////////////////////////////////////////////////////




///////////////////////////////////////////////////////////////////
// CORE CONFIGURATION                                            //
///////////////////////////////////////////////////////////////////


//path to images folder 
um.baseSRC = "udm-resources/";

 
//navbar orientation
um.orientation = [
	"horizontal",	// alignment ["vertical"|"horizontal"|"popup"|"expanding"]
	"left",		// h align ["left"|"right"]
	"top",		// v align ["top"|"bottom"]
	"relative",	// positioning ["relative"|"absolute"|"fixed"|"allfixed"]
	"0",	// x position ["em"|"ex"|"px"|"0"]
	"0",	// y position ["em"|"ex"|"px"|"0"]
	"10000",		// z order ["0" to "10000"] (menu takes 20000 headroom)
	];
	

//navbar list output
um.list = [
	"rigid",	// horizontal overflow ["rigid"|"flexible"]
	"yes",		// -SPARE-
	"no", 		// -SPARE-
	];


//menu behaviors	
um.behaviors = [
	"0",		// open timer ["milliseconds"|"0"]
	"0",		// close timer ["milliseconds"|"never"|"0"]
	"yes",	// reposition menus to stay inside the viewport ["yes"|"no"]
	"default",	// manage windowed controls for win/ie ["default","hide","iframe","none"]
	];


//reset behaviors
um.reset = [
	"yes",		// reset from document mouse click ["yes"|"no"]
	"yes",		// reset from window resize ["yes"|"no"]
	"yes",		// reset from text resize ["yes"|"no"]
	"no",		// reset after following link ["yes"|"no"]
	];


//horizontal continuation strip
um.hstrip = [
	"none",		// background ["color"|"#hex"|"rgb()"|"image.gif"|"none"]
	"yes",		// copy navbar item margin-right to margin-bottom ["yes"|"no"]
	];
	
	
	
	
///////////////////////////////////////////////////////////////////
// MODULE SETTINGS                                               //
///////////////////////////////////////////////////////////////////


//keyboard navigation [comment out or remove if not using]
um.keys = [
	"38",		// up ["n"] ("38" = up arrow key)
	"39",		// right ["n"] ("39" = right arrow key)
	"40",		// down ["n"] ("40" = down arrow key)
	"37",		// left ["n"] ("37" = left arrow key)
	"123",		// hotkey ["n"] ("38" = F12]
	"none",		// hotkey modifier ["none"|"shiftKey"|"ctrlKey"|"altKey"|"metaKey"]
	"27",		// escape ["n"|"none"] ("27" = escape key)
	"document.getElementsByTagName('a')[0]", // exit focus ["js-expression"]
	];




///////////////////////////////////////////////////////////////////
// NAVBAR DEFAULT STYLES                                         //
///////////////////////////////////////////////////////////////////


//styles which apply to the navbar
um.navbar = [
	"0",		// nav -> menu x-offset (+-)["n" pixels]
	"0",		// nav -> menu y-offset (+-)["n" pixels]
	"7.5em",	// width ["em"|"ex"|"px"] (vertical navbar only - horizontal navbar items have "auto" width) ("%" doesn't work right) 
	];


//styles which apply to each navbar item
um.items = [
	"0",		// margin between items ["n" pixels]
	"0",		// border size ["n" pixels] (single value only)
	"collapse",	// border collapse ["collapse"|"separate"] (only applies when margin = "0")
	"#458B00",// border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#010066",// hover/focus border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// hover/focus border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#010066",// visited border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid dashed solid solid",// visited border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"5",		// left/right padding ["n" pixels] (single value only)
	"0",		// top/bottom padding ["n" pixels] (single value only)
	"#000000",		// background ["color"|"#hex"|"rgb()"|"image.gif"]
	"#000000",		// hover/focus background ["color"|"#hex"|"rgb()"|"image.gif"]
	"#000000",		// visited background ["color"|"#hex"|"rgb()"|"image.gif"]
	"100%",		// font size ["em"|"ex"|"%"|"px"|"pt"|"absolute-size"|"relative-size"]
	"Verdana",// font family ["font1,font2,font3"] (always end with a generic family name)
	"normal",		// font weight ["normal"|"bold"|"bolder"|"lighter|"100" to "900"]
	"none",		// text decoration ["none"|"underline"|"overline"|"line-through"]
	"left",		// text-align ["left"|"right"|"center"]
	"#CCE8CF",	// color ["color"|"#hex"|"rgb()"]
	"#CCE8CF",	// hover/focus color ["color"|"#hex"|"rgb()"]
	"#CCE8CF",	// visited color ["color"|"#hex"|"rgb()"]
	"normal",	// font-style ["normal"|"italic"|"oblique"]
	"normal",	// hover/focus font-style ["normal"|"italic"|"oblique"]
	"normal",	// visited font-style ["normal"|"italic"|"oblique"]
	"letter-spacing:1px !important;",// additional link CSS (careful!)
	"",		// additional hover/focus CSS (careful!)
	"",		// additional visited CSS (careful!)
	"",// menu indicator character/image ["text"|"image.gif"|"none"] 
	"",// menu indicator rollover character/image ["text"|"image.gif"|"none"] (must be same type)
	"7",		// clipping width of indicator image ["n" pixels] (only when using image arrows)
	"..",		// alt text of indicator image ["text"] (only when using image arrows)
	];




///////////////////////////////////////////////////////////////////
// MENU DEFAULT STYLES                                           //
///////////////////////////////////////////////////////////////////


//styles which apply to each menu
um.menus = [
	"-7",		// menu -> menu x-offset (+-)["n" pixels]
	"-12",		// menu -> menu y-offset (+-)["n" pixels]
	"1",		// border size ["n" pixels] (single value only) 
	"#010066",// border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"20em",	// width ["em"|"ex"|"px"]
	"1",		// padding ["n" pixels] (single value only) 
	"#010066",	// background ["color"|"#hex"|"rgb()"|"image.gif"]
	"",		// additional menu CSS (careful!) (you can use a transition here but *not* a static filter)
	"",// shadow background ["color"|"#hex"|"rgb()"|"image.gif"|"none"]
	"2px",		// shadow offset (+-) ["em"|"px"|"pt"|"%"|"0"]
	"filter:alpha(opacity=50);",// additional shadow layer CSS (if you use a Microsoft.Shadow filter here then Win/IE5.5+ will do that *instead* of default shadow)
	];


//styles which apply to each menu item
um.menuItems = [
	"0",		// margin around items ["n" pixels] (single value only; margins are like table cellspacing)
	"1",		// border size ["n" pixels] (single value only)
	"separate",	// border collapse ["collapse"|"separate"] (only applies when margin = "0")
	"#458B00",	// border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#458B00",		// hover/focus border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// hover/focus border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#458B00",	// visited border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// visited border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"5",		// left/right padding ["n" pixels] (single value only) 
	"2",		// top/bottom padding ["n" pixels] (single value only) 
	"transparent",	// background ["color"|"#hex"|"rgb()"|"image.gif"]
	"#CCE8CF",	// hover/focus background ["color"|"#hex"|"rgb()"|"image.gif"]
	"transparent",	// visited background ["color"|"#hex"|"rgb()"|"image.gif"]
	"90%",		// font size ["em"|"ex"|"%"|"px"|"pt"|"absolute-size"|"relative-size"]
	"Verdana",// font family ["font1,font2,font3"] (always end with a generic family name)
	"normal",	// font weight ["normal"|"bold"|"bolder"|"lighter|"100" to "900"]
	"none",		// text decoration ["none"|"underline"|"overline"|"line-through"]
	"left",		// text-align ["left"|"right"|"center"]
	"#CCE8CF",		// color ["color"|"#hex"|"rgb()"]
	"#010066",		// hover/focus color ["color"|"#hex"|"rgb()"]
	"#CCE8CF",		// visited color ["color"|"#hex"|"rgb()"]
	"normal",	// font-style ["normal"|"italic"|"oblique"]
	"normal",	// hover/focus font-style ["normal"|"italic"|"oblique"]
	"normal",	// visited font-style ["normal"|"italic"|"oblique"]
	"",		// additional link CSS (careful!)
	"",		// additional hover/focus CSS (careful!)
	"",		// additional visited CSS (careful!)
	"",// submenu indicator character/image ["text"|"image.gif"|"none"] 
	"",// submenu indicator rollover character/image ["text"|"image.gif"|"none"] (must be the same type)
	"3",		// clipping width of indicator image ["n" pixels] (only when using image arrows)
	"..",		// alt text of indicator image ["text"] (only when using image arrows)
	];




///////////////////////////////////////////////////////////////////
// MENU CLASSES [comment out or remove if not using]             //
///////////////////////////////////////////////////////////////////


//classes which apply to menus [optional]
um.menuClasses["orangeMenu"] = [
	"#fdcb95 #a97742 #a97742 #fdcb95",// border colors ["color"|"#hex"|"rgb()"]
	"solid",	// border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"8em",		// width ["em"|"ex"|"px"]
	"#fec",		// background ["color"|"#hex"|"rgb()"|"image.gif"]
	"",		// additional menu CSS (careful!) (you can use a transition here but *not* a static filter)
	"",// shadow background ["color"|"#hex"|"rgb()"|"image.gif"|"none"] 
	"2px",		// shadow offset (+-) ["em"|"px"|"pt"|"%"|"0"]
	"filter:alpha(opacity=50);", // additional shadow layer CSS (if you use a Microsoft.Shadow filter here then Win/IE5.5+ will do that *instead* of default shadow)
	];


//classes which apply to menu items [optional]
um.itemClasses["orangeMenuItem"] = [
	"#fec",		// border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#edbb85",	// hover/focus border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// hover/focus border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#fec",		// visited border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// visited border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#fec",		// background ["color"|"#hex"|"rgb()"|"image.gif"]
	"#f8fbd0",	// hover/focus background ["color"|"#hex"|"rgb()"|"image.gif"]
	"#fec",		// visited background ["color"|"#hex"|"rgb()"|"image.gif"]
	"90%",		// font size ["em"|"ex"|"%"|"px"|"pt"|"absolute-size"|"relative-size"]
	"Verdana",// font family ["font1,font2,font3"] (always end with a generic family name)
	"normal",	// font weight ["normal"|"bold"|"bolder"|"lighter|"100" to "900"]
	"none",		// text decoration ["none"|"underline"|"overline"|"line-through"]
	"left",		// text-align ["left"|"right"|"center"]
	"#803090",	// color ["color"|"#hex"|"rgb()"]
	"#5656bd",	// hover/focus color ["color"|"#hex"|"rgb()"]
	"#803090",	// visited color ["color"|"#hex"|"rgb()"]
	"normal",	// font-style ["normal"|"italic"|"oblique"]
	"normal",	// hover/focus font-style ["normal"|"italic"|"oblique"]
	"normal",	// visited font-style ["normal"|"italic"|"oblique"]
	"",		// additional link CSS (careful!)
	"",		// additional hover/focus CSS (careful!)
	"",		// additional visited CSS (careful!)
	"",// submenu indicator character/image ["text"|"image.gif"|"none"] (must be the same type as default submenu indicator)
	"",// submenu indicator rollover character/image ["text"|"image.gif"|"none"] (must be the same type)
	"..",		// alt text of indicator image  ["text"] (only when using image arrow)
	];




///////////////////////////////////////////////////////////////////
// DYNAMIC MENUS                                                 //
///////////////////////////////////////////////////////////////////



	
