/* tooltipsy by Brian Cray
 * Lincensed under GPL2 - http://www.gnu.org/licenses/gpl-2.0.html
 * Option quick reference:
 * - alignTo: "element" or "cursor" (Defaults to "element")
 * - offset: Tooltipsy distance from element or mouse cursor, dependent on alignTo setting. Set as array [x, y] (Defaults to [0, -1])
 * - content: HTML or text content of tooltip. Defaults to "" (empty string), which pulls content from target element's title attribute
 * - show: function(event, tooltip) to show the tooltip. Defaults to a show(100) effect
 * - hide: function(event, tooltip) to hide the tooltip. Defaults to a fadeOut(100) effect
 * - delay: A delay in milliseconds before showing a tooltip. Set to 0 for no delay. Defaults to 200
 * - css: object containing CSS properties and values. Defaults to {} to use stylesheet for styles
 * - className: DOM class for styling tooltips with CSS. Defaults to "tooltipsy"
 * - showEvent: Set a custom event to bind the show function. Defaults to mouseenter
 * - hideEvent: Set a custom event to bind the show function. Defaults to mouseleave
 * Method quick reference:
 * - $('element').data('tooltipsy').show(): Force the tooltip to show
 * - $('element').data('tooltipsy').hide(): Force the tooltip to hide
 * - $('element').data('tooltipsy').destroy(): Remove tooltip from DOM
 * More information visit http://tooltipsy.com/
 *
 *
 * 2018-09-14 이주영
 *
 * from 속성을 추가하였습니다.
 * tooltipsy 생성시 from 속성을 입력하면,
 * 엘리먼트 생성시 from 속성을 추가합니다.
 *
 * click 이벤트시 삭제, 또는 변화 행동을 추가해주시면 됩니다.
 *
 * 예시)
	$(".tooltip").tooltipsy({
		delay: 0,
		offset: [5, 5],
		css: {
			'font-size' : '12px',
			'font-weight' : 'bold',
			'padding': '10px',
			'color': '#303030',
			'background-color': '#ffffff',
			'border': '2px solid #4893BA',
			'-moz-box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
			'-webkit-box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
			'box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
			'text-shadow': 'none'
		},
		from: 고유값
	}).click(function (e) {
		$('.tooltipsy').parent().hide();

		var from = 고유값;
		if(from != null){
			setTimeout(function(){
				if(!$("div[속성명=" + 고유값 + "]").is(":visible")){
					$("div[from=" + 고유값 + "]").remove();
				}
			}, 500);
		}
	});
 */
;
(function(a) {
	a.tooltipsy = function(c, b) {
		this.options = b;
		this.$el = a(c);
		this.title = this.$el.attr("title") || "";
		this.$el.attr("title", "");
		this.random = parseInt(Math.random() * 10000);
		this.ready = false;
		this.shown = false;
		this.width = 0;
		this.height = 0;
		this.delaytimer = null;
		this.$el.data("tooltipsy", this);
		this.init()
	};
	a.tooltipsy.prototype = {
		init : function() {
			var e = this, d, b = e.$el, c = b[0];
			e.settings = d = a.extend({}, e.defaults, e.options);
			d.delay = +d.delay;
			if (typeof d.content === "function") {
				e.readify()
			}
			if (d.showEvent === d.hideEvent && d.showEvent === "click") {
				b.toggle(function(f) {
					if (d.showEvent === "click" && c.tagName == "A") {
						f.preventDefault()
					}
					if (d.delay > 0) {
						e.delaytimer = window.setTimeout(function() {
							e.show(f)
						}, d.delay)
					} else {
						e.show(f)
					}
				}, function(f) {
					if (d.showEvent === "click" && c.tagName == "A") {
						f.preventDefault()
					}
					window.clearTimeout(e.delaytimer);
					e.delaytimer = null;
					e.hide(f)
				})
			} else {
				b.bind(d.showEvent, function(f) {
					if (d.showEvent === "click" && c.tagName == "A") {
						f.preventDefault()
					}
					e.delaytimer = window.setTimeout(function() {
						e.show(f)
					}, d.delay || 0)
				}).bind(d.hideEvent, function(f) {
					if (d.showEvent === "click" && c.tagName == "A") {
						f.preventDefault()
					}
					window.clearTimeout(e.delaytimer);
					e.delaytimer = null;
					e.hide(f)
				})
			}
		},
		show : function(i) {
			if (this.ready === false) {
				this.readify()
			}
			var b = this, f = b.settings, h = b.$tipsy, k = b.$el, d = k[0], g = b
					.offset(d);
			if (b.shown === false) {
				if ((function(m) {
					var l = 0, e;
					for (e in m) {
						if (m.hasOwnProperty(e)) {
							l++
						}
					}
					return l
				})(f.css) > 0) {
					b.$tip.css(f.css)
				}
				b.width = h.outerWidth();
				b.height = h.outerHeight()
			}
			if (f.alignTo === "cursor" && i) {
				var j = [ i.clientX + f.offset[0], i.clientY + f.offset[1] ];
				if (j[0] + b.width > a(window).width()) {
					var c = {
						top : j[1] + "px",
						right : j[0] + "px",
						left : "auto"
					}
				} else {
					var c = {
						top : j[1] + "px",
						left : j[0] + "px",
						right : "auto"
					}
				}
			} else {
				var j = [
						(function() {
							if (f.offset[0] < 0) {
								return g.left - Math.abs(f.offset[0]) - b.width
							} else {
								if (f.offset[0] === 0) {
									return g.left
											- ((b.width - k.outerWidth()) / 2)
								} else {
									return g.left + k.outerWidth()
											+ f.offset[0]
								}
							}
						})(),
						(function() {
							if (f.offset[1] < 0) {
								return g.top - Math.abs(f.offset[1]) - b.height
							} else {
								if (f.offset[1] === 0) {
									return g.top
											- ((b.height - b.$el.outerHeight()) / 2)
								} else {
									return g.top + b.$el.outerHeight()
											+ f.offset[1]
								}
							}
						})() ]
			}
			h.css({
				top : j[1] + "px",
				left : j[0] + "px"
			});
			b.settings.show(i, h.stop(true, true))
		},
		hide : function(c) {
			var b = this;
			if (b.ready === false) {
				return
			}
			if (c && c.relatedTarget === b.$tip[0]) {
				b.$tip.bind("mouseleave", function(d) {
					if (d.relatedTarget === b.$el[0]) {
						return
					}
					b.settings.hide(d, b.$tipsy.stop(true, true))
				});
				return
			}
			b.settings.hide(c, b.$tipsy.stop(true, true))
		},
		readify : function() {
			this.ready = true;
			this.$tipsy = a(
					'<div id="tooltipsy'
							+ this.random
							+ '" style="position:fixed;z-index:2147483647;display:none" from="' + this.options.from + '">')
					.appendTo("body");
			this.$tip = a('<div class="' + this.settings.className + '">')
					.appendTo(this.$tipsy);
			this.$tip.data("rootel", this.$el);
			var c = this.$el;
			var b = this.$tip;
			this.$tip
					.html(this.settings.content != "" ? (typeof this.settings.content == "string" ? this.settings.content
							: this.settings.content(c, b))
							: this.title)
		},
		offset : function(b) {
			return this.$el[0].getBoundingClientRect()
		},
		destroy : function() {
			if (this.$tipsy) {
				this.$tipsy.remove();
				a.removeData(this.$el, "tooltipsy")
			}
		},
		defaults : {
			alignTo : "element",
			offset : [ 0, -1 ],
			content : "",
			show : function(c, b) {
				b.fadeIn(100)
			},
			hide : function(c, b) {
				b.fadeOut(100)

				/*var from = b.attr("from");
				if(from != null){
					setTimeout(function(){
						console.log($("div[aria-describedby=" + from + "]").is(":visible"));
						console.log($("div[aria-describedby=" + from + "]").length);
						if(!$("div[aria-describedby=" + from + "]").is(":visible")){
							$("div[from=" + from + "]").remove();
						}
					}, 1000);
				}*/
			},
			css : {},
			className : "tooltipsy",
			delay : 200,
			showEvent : "mouseenter",
			hideEvent : "mouseleave",
			form : ""
		}
	};
	a.fn.tooltipsy = function(b) {
		return this.each(function() {
			new a.tooltipsy(this, b)
		})
	}
})(jQuery);