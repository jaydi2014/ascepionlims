// UDMv4.44 //
///////////////////////////////////////////////////////////////////
//                                                               //
//  ULTIMATE DROP DOWN MENU Version 4.44 by Brothercake          //
//  http://www.udm4.com/                                         //
//                                                               //
//  This script may not be used or distributed without license   //
//                                                               //
///////////////////////////////////////////////////////////////////
function umKM(){um.kbm=this;um.ha=0;um.fkd=0;um.tf=null;um.mt=[um.e[10],um.e[11]];if(um.kb&&um.m.cookie){var f=[um.gd('hotkeySelector'),um.gd('modifierSelector')];var c=um.m.cookie.split(';');var n=c.length;i=0;do{if(/udmKeyPrefs/.test(c[i])){var a=c[i].split('=')[1].split(',');j=0;do{um.keys[j+4]=a[j];if(f[j]){var z=f[j].options;var l=z.length;var k=0;do{if(z[k].value==a[j]){z[k].selected=1;break;}k++;}while(k<l);}j++;}while(j<2);break;}i++;}while(i<n);}};um.keyPrefs=function(){if(!(um.kb&&um.d)){alert('Sorry,this feature is not supported in your browser.');return false;}var d=new Date();d.setTime(d.getTime()+(365*24*60*60*1000));um.m.cookie='udmKeyPrefs=test; expires='+d.toGMTString()+'; path=/';if(!um.m.cookie){alert('Sorry,your browser didn\'t accept the cookie.\nWe cannot save your settings.');}else{var f=[um.gd('hotkeySelector'),um.gd('modifierSelector')];i=0;do{um.keys[i+4]=f[i].options[f[i].options.selectedIndex].value;i++;}while(i<2);um.m.cookie='udmKeyPrefs='+um.keys[4]+','+um.keys[5]+'; expires='+d.toGMTString()+'; path=/';alert('Save successful!');}return true;};umKM.prototype.bdh=function(){if(typeof document.addEventListener!=um.un){if(um.s){var self=this;document.addEventListener('keydown',function(e){if(um.fkd){return;}um.fkd=1;self.kha(e);},0);document.addEventListener('keyup',function(){um.fkd=0;},0);}else{document.addEventListener('keypress',this.kha,0);}}else{document.attachEvent('onkeydown',this.kha);}};umKM.prototype.bfh=function(){document.addEventListener('mouseover',function(e){if(um.ha&&um.kdf&&!umTree.contains(e.target)){um.cm(e);um.ha=0;}},0);};umKM.prototype.bth=function(l){var a=um.gc(l);var c=um.es(a.className);if(/nohref/i.test(c)){um.kbm.cdl(a);}if(um.ie){return false;}a.addEventListener('focus',function(e){if((!um.o7&&!um.lf)||(um.o7&&um.kdf)){um.bub=0;l.over(1,e.target);}},0);return true;};umKM.prototype.cu=function(m,l,t){var v=[null,null,null];if((m!=null&&m.style.visibility!='visible')||m==null){if(l.previousSibling){v[0]=l.previousSibling;}if(l.nextSibling){v[1]=l.nextSibling;}}m=(um.gu(um.gp(t)).length>0)?um.gu(um.gp(t))[0]:null;if(m!=null&&typeof m.style!=um.un&&m.style.visibility=='visible'){var r=m.getElementsByTagName('li');var n=r.length;j=0;do{v[v.length]=r[j++];}while(j<n);}if(um.tf!=null){r=um.gp(um.tf).parentNode.lastChild;if(um.gp(um.tf)==r){um.n.lr(um.gc(r),0);}}n=v.length;i=0;do{if(v[i]!=null){if(um.gu(v[i]).length>0){um.n.cp(um.gu(v[i])[0],v[i]);}else{um.n.cp(null,v[i]);}}i++;}while(i<n);};umKM.prototype.cdl=function(l){l.href=um.jv;l.style.cursor='default';};umKM.prototype.mkc=function(k){for(i=1;i<4;i+=2){if(k==um.keys[i]){k=um.keys[4-i];break;}}return k;};umKM.prototype.kha=function(e){if(!e){e=window.event;}k=e.keyCode;if(!um.kb&&k!=9){return false;}if(k==um.keys[6]){um.ha=1;}if((k==um.keys[4] &&((um.keys[5]=='none'&&!e.shiftKey&&!e.ctrlKey&&!e.altKey&&!e.metaKey)||e[um.keys[5]]))||(k==um.keys[6])){um.e[10]=1;um.e[11]=1;if(!um.ha){um.cm(e);um.fl.focus();um.ha=1;um.ap('080',um.tr);}else{if(um.sp){um.sapi.voice.Speak(um.vocab[8],2);}um.cm(e);if(um.wie50&&um.e[13]=='yes'){um.n.ts('visible');}eval(um.keys[7]).focus();um.e[10]=um.mt[0];um.e[11]=um.mt[1];um.ha=0;um.ap('090',um.tr);}}var a=(e.target)?e.target:e.srcElement;if(um.tr.contains(a)){um.e[10]=1;um.e[11]=1;var c=um.es(um.gp(a).parentNode.className);if((um.h&&c=='udm')||typeof um.hmx=='boolean'){if(um.nm&&(k==um.keys[0]||k==um.keys[2])){return false;}i=0;do{if(k==um.keys[i]){k=um.keys[um.rtl?(i-1):(3-i)];break;}i++;}while(i<4);}else{if(um.nm&&(k==um.keys[1]||k==um.keys[3])){return false;}var t=um.gp(a).parentNode;if(um.a||um.e[12]=='yes'){c=um.es(t.className);if(um.gu(um.gp(a))[0]){um.xm=um.gu(um.gp(a))[0];if(um.getRealPosition(um.xm,'x')<um.getRealPosition(t,'x')){k=um.kbm.mkc(k);}}else if(c!='udm'){um.pm=um.gp(t).parentNode;if(um.getRealPosition(um.pm,'x')>um.getRealPosition(t,'x')){k=um.kbm.mkc(k);}}}}um.tf=null;var l=umTree.getElementsByTagName('li');var n=l.length;switch(k){case 9 :i=0;do{if(l[i]==um.gp(a)){um.tf=a;if(e.shiftKey){var p=(i==0)?-1:i-1;}else{p=((i+1)==n)?-1:i+1;}if(p<=-1){setTimeout('um.closeAllMenus()',55);}break;}i++;}while(i<n);break;case um.keys[0] :if(um.gp(a).previousSibling){var s=um.gp(a).previousSibling;if(s){t=um.gc(s);var f=(typeof t!=um.un)?t:null;if(f){f.focus();}}}else if(um.gp(a).parentNode.childNodes.length>1){um.n.cp(um.gu(um.gp(a))[0],um.gp(a));t=um.gc(um.gp(a).parentNode.lastChild);f=(um.gp(a).parentNode.className!='udm');if(f&&um.h&&um.gp(um.gp(a).parentNode).parentNode.className=='udm'){t=um.gc(um.gp(um.gp(a).parentNode));}t.focus();}um.ap('100',a);if(um.ie){return false;}else if(e){e.preventDefault();}break;case um.keys[1] :if(um.gu(um.gp(a))[0]){t=um.gu(um.gp(a))[0];f=(t)?um.gc(t):null;if(f){f.focus();}}um.ap('101',a);if(um.ie){return false;}else if(e){e.preventDefault();}break;case um.keys[2] :if(um.gp(a).nextSibling){s=um.gp(a).nextSibling;if(s){t=um.gc(s);f=(typeof t!=um.un)?t:null;if(f){f.focus();}}}else if(um.gp(a).parentNode.childNodes.length>1){um.n.cp(um.gu(um.gp(a))[0],um.gp(a));um.gc(um.gp(a).parentNode.firstChild).focus();}um.ap('102',a);if(um.ie){return false;}else if(e){e.preventDefault();}break;case um.keys[3] :if(um.gp(a).parentNode.parentNode){t=um.gp(a).parentNode;f=(t.className=='udm')?null:um.gc(um.gp(t));if(f&&(typeof f.focus=='function'||typeof f.focus=='object')){f.focus();}}um.ap('103',a);if(um.ie){return false;}else if(e){e.preventDefault();}break;}}return true;};umKM.prototype.cws=function(n){if(um.mie){return false;}for(var x=0;x<n.childNodes.length;x++){var k=n.childNodes[x];if((k.nodeType==3)&&(!/\S/.test(k.nodeValue))){n.removeChild(n.childNodes[x]);x--;}if(k.nodeType==1){this.cws(k);}}return n;};