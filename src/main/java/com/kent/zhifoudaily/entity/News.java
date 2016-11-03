package com.kent.zhifoudaily.entity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Kent ↗↗↗ on 2016/11/2.
 */

public class News {

    /**
     * body : <div class="main-wrap content-wrap">
     <div class="headline">

     <div class="img-place-holder"></div>



     </div>

     <div class="content-inner">




     <div class="question">
     <h2 class="question-title"></h2>

     <div class="answer">

     <div class="content">
     <p>回答来自<a href="https://www.zhihu.com/org-intro">机构帐号</a>：<a class="author-link" href="https://www.zhihu.com/org/qi-mu-ren-sheng-yin-yue-ju-22" target="_blank">七幕人生音乐剧</a></p>
     </div>
     </div>


     </div>





     <div class="question">
     <h2 class="question-title"></h2>

     <div class="answer">

     <div class="meta">
     <img class="avatar" src="http://pic3.zhimg.com/3d8a1cbaedacc5842aa791ae00eeb30a_is.jpg">
     <span class="author">七幕人生音乐剧，</span><span class="bio">七幕人生，你的第一场音乐剧！</span>
     </div>

     <div class="content">
     <p>七幕君不邀自答，从我最擅长的音乐剧领域下笔，给大家分享几个&ldquo;完美犯罪&rdquo;的&ldquo;真&rdquo;实案例。</p>
     <p><strong>&ldquo;真&rdquo;&middot;完美犯罪&middot;案例一 杀人不偿命的女人，们。</strong></p>
     <p>大名鼎鼎的《芝加哥》（<em>Chicago</em>），它的故事来源于芝加哥论坛报的一则报道，两位女性以相似的罪行被控谋杀。片中有一句经典歌词，叫做&ldquo;杀人可以是一门艺术&rdquo;。Bob Fosse 和 Fred Ebb 两位大神在 1975 年在百老汇推出了音乐剧版《芝加哥》，2002 年被搬上大荧幕，获奥斯卡最佳影片奖，可能大部分人对泽塔 - 琼斯和齐薇格两位女神在电影中飙戏飚歌飚舞的画面还记忆犹新。</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-f0317140d736ffaef7f1ab9c6c52f884_b.jpg" alt="" /></p>
     <p>标题里说杀人不偿命的女人，正是电影中两位主角的原型。</p>
     <p><strong>No.1 Velma Kelly 的原型&mdash;&mdash;Belva Gaertner</strong></p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-d95105fa682438b5c9441db75f066be8_b.jpg" alt="" /></p>
     <p>1924 年 3 月 11 日，警方在街旁的汽车内发现一具男尸，经鉴定男人死于枪杀。警方在车内找到了凶器 手枪和一瓶酒（当时正属禁酒令期间）。在接下来的调查中，警方确认了死者的身份，名叫 Walter Law，而后又顺瓜摸藤找到了这辆车的车主，正是当地舞厅歌手 贝尔瓦.盖特纳（Belva Gaertner），又刚巧发现两人的关系竟是情人（结婚出轨的戏码）。</p>
     <p>3 月 12 日，当警方追抓捕贝尔瓦时，在她家找到了一件沾血的衣服，她承认自己之前确实跟情夫在车里，不过她后来喝醉了，什么都不记得了。而车上的枪是她的，但只是用来防火防强盗的~</p>
     <p>在这种情况下，贝尔瓦自然成了第一嫌疑人。</p>
     <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-2021e6e5561d8d2653db500742118b05_b.jpg" alt="" /></p>
     <p>紧接着，Walter Law 的一位同事跟警方反应，贝尔瓦是个占有欲很强的人，有一次 Walter 想离开她的时候，她甚至还用刀威胁他。Law 相信万一哪天自己死了，99% 是被贝尔瓦搞死的。</p>
     <p>贝尔瓦辩解道：&ldquo;女人才不会因为自己强烈的爱就杀死她的男人，根本不值得啊，毕竟世界上还有大把其他的好男人。况且 Law 对我来说只是个孩子，他 29 我 38。&rdquo;</p>
     <p>在随后的审判中，贝尔瓦请到了一个牛逼的律师，让陪审团相信死去的 Walter Law 极有可能是自杀。最终，法庭宣布贝尔瓦被无罪释放。</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-a9ae71f147060f068aa7caa81da310a4_b.jpg" alt="" /></p>
     <p>后话是贝尔瓦跟前夫复婚了，沉溺在酒精和虐待他人的快感中，再后来跟前夫搬到欧洲。1948 年她前夫去世，她活到 80 岁。</p>
     <p><strong>No.2 Roxie Hart 的原型&mdash;&mdash;Beulah Annan</strong></p>
     <p><img class="content-image" src="http://pic3.zhimg.com/70/v2-8566782c4b157e4de87feacdf4e29316_b.jpg" alt="" /></p>
     <p>在贝尔瓦.盖特纳案件发生后的几周，也就是 1924 年 4 月 3 日，又发生了一起相似的案件。已婚之妇比尤莱.安南（Beulah Annan）被控谋杀了她的情夫。在法庭上，她声称开枪是为了自卫，&ldquo;因为我们同时碰到了枪&rdquo;。之后，比尤莱也被无罪释放。一时间，贝尔瓦和比尤莱成为了小报头条的常客。</p>
     <p><img class="content-image" src="http://pic3.zhimg.com/70/v2-59b2b59af57753369c27cbabae27a792_b.jpg" alt="" /></p>
     <p>给大家推荐一个片段：<a href="http://v.youku.com/v_show/id_XMTQ4NTYxNDg4NA==.html?from=s1.8-1-1.2">【七幕人生】电影《芝加哥》片段《We Both Reached For the Gun》&mdash;在线播放&mdash;优酷网，视频高清在线观看</a></p>
     <p>其实贝尔瓦和比尤莱的&ldquo;完美犯罪&rdquo;并不是多高明，只是没有直接的证据表明她们确实杀了人，所以嫌疑人在辩解中存在一定侥幸。</p>
     <p>不过接下来介绍的另一个真实案例，则是&ldquo;接近完美&rdquo;&mdash;&mdash;完美的是犯罪手段，他可是伪造支票套取百万现金，摇身一变成泛美飞行员的诈骗大亨；不完美之处在于天网恢恢疏而不漏，再狡猾的狐狸也难逃猎人的围捕。</p>
     <p><strong>&ldquo;真&rdquo;&middot;完美犯罪&middot;案例二 猫，最终还是会抓到老鼠的。</strong></p>
     <p>让我想到这个案例的并不是《猫》哟，而是一部叫做《猫鼠游戏》（Catch Me If You Can）的作品，就是你们小李子演了（并没拿奖）接下来要介绍的这位&ldquo;完美犯罪&rdquo;典范&mdash;&mdash;弗兰克&middot;阿巴内尔（Frank William Abagnale, Jr.）。</p>
     <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-3c444632893c559e9f5547f9c66fb44b_b.jpg" alt="" /></p>
     <p>弗兰克&middot;阿巴内尔的一生，是传奇的一生，他是 20 世纪最大的诈骗艺术家。</p>
     <p>21 岁之前，弗兰克&middot;阿巴内尔伪造过支票骗钱，冒充过各种身份（飞行员啊、医生啊、律师啊之类的），成功地从拘留所脱逃两次，是让美国、意大利、西班牙、土耳其、德国等多个国家咬牙切齿的重要通缉犯。</p>
     <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-4a6911296dff93256613e10f68ef0735_b.jpg" alt="" /></p>
     <p>26 岁之后，经过 5 年监狱生活的锻炼，弗兰克&middot;阿巴内尔出狱了。出狱后改邪归正的他，协助 FBI 抓获了一批又一批的金融诈骗高手。如今，他已经是联邦调查局的顾问了。对了，出狱后，弗兰克&middot;阿巴内尔还写了一本自传，名字就叫 Catch Me If You Can</p>
     <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-4392bc80c13d677ca97ad8b3c74e4b59_b.jpg" alt="" /></p>
     <p>2002 年，大导演<a href="http://baike.baidu.com/view/20381.htm">斯皮尔伯格</a>以阿巴内尔的自传改编的电影《<a href="http://baike.baidu.com/view/334823.htm">猫鼠游戏</a>》上映，你们的小李子和汤姆&middot;汉克斯上演了一出&ldquo;猫捉老鼠&rdquo;的精彩戏码！顺便一提，阿巴内尔在影片中客串了一名法国警官。</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-c54659265997130a66cbe05285963f4c_b.jpg" alt="" /></p>
     <p>2011 年，同名音乐剧在美国百老汇的 Neil Simon Theatre 上演，饰演弗兰克&middot;阿巴内尔这个角色的是你们的 Broadway Boyfriend&mdash;&mdash;Aaron Tveit！</p>
     <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-c6b23e4041cf2c77fb3b1f4da622566f_b.jpg" alt="" /></p>
     <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-a68ae5833a7afa0531d42e99fcbfd4eb_b.jpg" alt="" /></p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-670b4437ec267e3f67df3fe8589977ac_b.jpg" alt="" /></p>
     <p>附赠演出片段&rarr;<a href="http://v.youku.com/v_show/id_XMTUwMDUyMzIzNg==.html?from=s1.8-1-1.2">【七幕人生】音乐剧《CATCH ME IF YOU CAN》托尼奖表演《Don't Break The Rules》</a></p>
     <p>看完大荧幕和舞台上的弗兰克&middot;阿巴内尔形象，我下面讲两个他比较牛逼的犯罪案例&mdash;&mdash;</p>
     <p><strong>以你支票 印我之&ldquo;名&rdquo;</strong></p>
     <p>弗兰克&middot;阿巴内尔比较有名的套现手法一共分三步：第一步，去银行取一些空白存款单；第二步，把自己存款单的账户号码打印到空白存款单上；第三步，把这些空白存款单放回银行。如此之后，我们的阿巴内尔就静等着收钱，因为其他储户存的钱并没有到达指定的账户，而是打到了他的户头上（此处利用了人们对银行数字代码的无知）。靠着伪造支票的娴熟手段和完美演技，阿巴内尔在 21 岁之前，游玩了 26 个国家伪造了 250 万美元的支票，戏耍了一批又一批的银行，而且完全没被抓住过。</p>
     <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-c8ca342c89f1fd28297e869dcd215173_b.jpg" alt="" /></p>
     <p><strong>冒充泛美航空飞行员 衣食住行你买单</strong></p>
     <p>弗兰克&middot;阿巴内尔冒充航空公司飞行员的行为可谓大胆至极，因为他并不会开飞机。但为了免费坐飞机，他找了借口打电话给泛美航空，说自己的制服在送洗的时候丢了，而后又通过伪造的员工证件加持，顺利地拿到了这件制服。戏要做全套，他还伪造了一份美国联邦航空管理局颁发的飞行执照！当然你可能不相信，冒充飞行员的阿巴内尔那时只有 16 岁！据泛美航空的统计，阿巴内尔在泛美的两年，利用伪造的员工身份，享受了至少 250 多次的免费飞行，总航程超过 1,600,000 公里，范围遍及全球 26 个国家。当然除了飞机上的一切，他还享受了免费的旅店、美食，费用均由航空公司买单！</p>
     <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-a2ca22101363cf9166f013f13c8b8539_b.jpg" alt="" /></p>
     <p>介绍完毕，大家可以找找弗兰克&middot;阿巴内尔的自传和相关电影电视剧，更全面地看看这位诈骗艺术家的诈骗之道。（会比我描述的更好哟！</p>
     <p>P.S.前面我预警过，这位&ldquo;艺术家&rdquo;最终是难逃法网的。不过抓捕他的过程，并没有玩出电影常见的抓捕花样手段：那段时间阿巴内尔藏身纽约，有一天，他走在路上的时候突然被叫到名字，下意识地回头后，两个人冲上来把他抓了。原来这两个人正是便衣侦探，在热狗店里无意中瞥见大街上有个人和他们追踪多日的阿巴内尔长得非常像，但又不敢确认。其中一个侦探情急生智，冲着外面大喊一声：&ldquo;弗兰克！&rdquo;后来阿巴内尔自嘲地说：&ldquo;这个例子可以证明，再精明的人也有犯低级错误的时候。&rdquo;</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-2e04fb838fdef0dd19c4c4ae34b97a80_b.jpg" alt="" /></p>
     <p>当然会犯低级错误的不只他一个，下面再介绍一个百密一疏的案例。</p>
     <p><strong>&ldquo;真&rdquo;&middot;完美犯罪&middot;案例三 精心设计反成一场空</strong></p>
     <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-1b0b81cc09664d958091c61732a1f569_b.jpg" alt="" /></p>
     <p>1924 年 5 月，人们在芝加哥的一处郊区发现一具尸体。这个年仅 14 岁的生命叫 Frank ，被随意地扔在阴沟里，头部遭受重击，身上衣不蔽体。警方在现场找到一副眼镜，由此揭开了杀人凶手的真面目。</p>
     <p>两个凶手是不到 20 岁的青年，分别叫 Nathan Leopold 和 Richard Loeb，人们称之为 Thrill Killers。两人对 Frank 的残杀是一场无差别谋杀，只为了证明自己能造成一起&ldquo;完美谋杀案&rdquo;。</p>
     <p><img class="content-image" src="http://pic3.zhimg.com/70/v2-8f805d9a6086404c562efcb083a94c4a_b.jpg" alt="" /></p>
     <p>Nathan 和 Richard 是一对不差钱的同窗好友，除了友谊，他们还分享性爱（这才是真&middot;好基友），当然还有犯罪。两人一开始只是做不痛不痒的事儿，偷别的刀、相机，后面就纵火。不过这些犯罪行为渐渐不能满足二人的欲望，他们琢磨着要策划一起完美谋杀来吸引公众的注意力。</p>
     <p>他们花了 7 个月的时间来策划这起谋杀，实施犯罪行为的那一天，Nathan 和 Richard 将早早准备的谋杀器具放到车里。然后找到了 Frank ，将其哄骗到车上，接着 Richard 用凿子照着 Frank 的头重击一下。Frank 很快昏了过去，并由于失血过多在 15 分钟之内死亡。接着，二人就把尸体扔到了阴沟里，并 向尸体上泼了一瓶硫酸。之后回到家给 Frank 的父母打了一通电话勒索赎金。</p>
     <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-4a7853f84b7434d51398cc9ffbb49f4b_b.jpg" alt="" /></p>
     <p>第二天，Richard 还在为自己的完美谋杀洋洋得意时，Nathan 却发现尸体被发现并见诸报端。更可怕的是，警察还拿着 Nathan 的眼镜找上了门。Nathan 那不太起眼的眼镜，却是有些来头。在芝加哥，这种由独特铰链机制制成的眼镜，只有三个人买过，其中一人就是 Nathan Leopold。最终二人被捕。</p>
     <p><img class="content-image" src="http://pic3.zhimg.com/70/v2-b61065a90d9abdfedde4ce58fa9de592_b.jpg" alt="" /></p>
     <p>根据两人的真实犯罪经历，1959 年，美国导演 Richard Fleischer 推出了一部名叫《朱门孽种》（Compulsion）的电影，Nathan 曾试图阻止这部电影的问世，因为这侵犯他的隐私。</p>
     <p><img class="content-image" src="http://pic3.zhimg.com/70/v2-6bb257bae95ae6c0049b7187eb986dda_b.jpg" alt="" /></p>
     <p>2003 年，同样根据这个案件改编的音乐剧《Thrill Me》在纽约首演，2005 年登陆百老汇，全剧只有两位演员，一架钢琴，一位琴师和一个场景，堪称小剧场的经典之作。不过更戏剧的是，Nathan 粗心大意丢的眼镜，其实是故意的。他只是为了让警察把自己和 Richard 关在一起，后面再请律师辩护，让两人在逃脱死刑，永久监禁，永远在一起。</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-aaaf906b170e3e028dfe9c20a5c2546c_b.jpg" alt="" /></p>
     <p>2016 年，PBS 美国公共电视网推出了《Thrill Me》原型案件的纪录片&mdash;&mdash;The Perfect Crime。</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-bd238b254b7ad28b8d3b43c09d5f2ca0_b.jpg" alt="" /></p>
     <p>写到这儿我发现，虽然我是为了完美犯罪来写的，但介绍的三个都不是顶级的完美犯罪。索性抛开&ldquo;完美&rdquo;两字，我再追加介绍两个根据真实犯罪事件改编而成的音乐剧&mdash;&mdash;</p>
     <p><strong>音乐剧《美国精神病人》（<em>American Psycho</em>）</strong>，根据布莱特&middot;伊斯顿&middot;埃利斯的同名小说改编而成。剧中主人公帕特里克.贝特曼白天是一个安静、无趣的华尔街投行家。而到了夜晚，他则化身为恶魔，在城市中寻觅猎物，并以残忍的方式将她们杀死。</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-3eeb54d608490b6fa3be272c3250d044_b.jpg" alt="" /></p>
     <p>作者埃利斯在接受采访时表示，他以一些当时真实存在的连环杀人犯为原型创作了这个人物。这些杀人犯中包括泰德&middot;邦迪（Ted Bundy）和理查德.蔡司（Richard Chase），前者强奸并谋杀了 35 名女性，他的故事曾于 2002 年被拍成电影《美色连环奸杀》（Ted Bundy）。而后者曾谋杀 6 人，由于他会在杀人后吸食被害者的血液并吞食他们的尸体，所以也被称为&ldquo;萨克拉门托的吸血鬼&rdquo;。</p>
     <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-c5511e7576632a7e196ccf7242b34919_b.jpg" alt="" /></p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-be7758c8ec90128bae626c7324e2e424_b.jpg" alt="" /></p>
     <p><strong>音乐剧《理发师陶德》（<em>Sweeney Todd</em>）</strong>，历史学家彼得.海宁认为&ldquo;理发师陶德&rdquo;确有其人。他声称陶德于 1756 年 10 月 26 日出生在东伦敦红砖巷，从小就对各类刑具十分着迷。在他的父母双双离世后，14 岁的陶德成为了一名擅长制作剃刀的刀匠的学徒。之后他因为小偷小摸而入狱，在狱中，他成为了囚犯们的理发师。</p>
     <p><img class="content-image" src="http://pic3.zhimg.com/70/v2-cde07f682579e1d55984c3a2c4055c16_b.jpg" alt="" /></p>
     <p>获释后，他靠当理发师谋生，并和一名他经常虐待的年轻女性同居。某日，一位客人和陶德聊起了前一晚的艳遇，而陶德发现这位客人所描述的艳遇对象特征与自己的同居女友相符。一怒之下，陶德用剃刀割断了这位客人的喉咙。</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-c55c294bbae81132913a11a37c74dd90_b.jpg" alt="" /></p>
     <p>从这以后，陶德开始杀害那些看起来家底殷实的客人，以掠夺他们的财富，但尸体的处置成了问题。这时，开肉馅饼店的洛薇特夫人出现了，两人成为了共犯。慢慢的，肉馅饼店中的异味引起了警方的注意，洛薇特夫人被逮捕，并指认了陶德。最终，陶德被判谋杀，并于 1802 年 1 月 25 日被执行绞刑，他的尸体则交由皇家外科医学院解剖处置。</p>
     <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-81bfda2a5b00c146d223dc0d6be38519_b.jpg" alt="" /></p>
     <p><strong>以上，分享完毕！</strong></p>
     </div>
     </div>


     <div class="view-more"><a href="http://www.zhihu.com/question/27671276">查看知乎讨论<span class="js-question-holder"></span></a></div>

     </div>





     <div class="question">
     <h2 class="question-title"></h2>

     <div class="answer">

     <div class="content">
     <p>「知乎<span class="lG">机构</span><span class="lG">帐号</span>」是<span class="lG">机构</span>用户专用的知乎<span class="lG">帐号</span>，与知乎社区内原有的个人<span class="lG">帐号</span>独立并行，其使用者为有正规资质的组织<span class="lG">机构</span>，包括但不限于科研院所、公益组织、政府机关、媒体、企业等。这不仅是知乎对<span class="lG">机构</span>的「身份认证」，更是涵盖了内容流通机制、<span class="lG">帐号</span>规范等全套<span class="lG">帐号</span>体系。和个人<span class="lG">帐号</span>一样，<span class="lG">机构</span><span class="lG">帐号</span>开通不需要任何费用，同时也受社区规范的监督管理，并要遵守相关协议。目前<span class="lG">机构</span><span class="lG">帐号</span>入驻采用邀请制。您可以通过 &nbsp;<a href="http://zhihu.com/org-intro" target="_blank">什么是「知乎机构帐号」</a>&nbsp;来了解更多<span class="lG">机构</span><span class="lG">帐号</span>信息。</p>
     </div>
     </div>


     </div>


     </div>
     </div>
     * image_source : 《理发师陶德》
     * title : 有哪些「完美犯罪」的真实案例？
     * image : http://pic3.zhimg.com/24d8634064e907b498ba4d9c93f47196.jpg
     * share_url : http://daily.zhihu.com/story/8939056
     * js : []
     * ga_prefix : 110115
     * images : ["http://pic2.zhimg.com/5327c840eece7222b46dc1a13b478a85.jpg"]
     * type : 0
     * id : 8939056
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<String> js;
    private List<String> images;
    private List<String> css;

    public static News objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), News.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
