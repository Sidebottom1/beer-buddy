var Popup = function() {

	var self = this;
	var box = $('<div/>').addClass('popup'); //the poopup box that contains the content
	var backdrop = $('.backdrop');
	var content = null;
	var listeners = false;
	var returnSet = false;
	var returnFiller = '';

	if(backdrop.length === 0) {
		backdrop = $('<div/>').addClass('backdrop');
		$('body').prepend(backdrop);
	}

	$('body').prepend(box);


	//Private function to center the box
	var centerBox = function() {

		var win = $(window);
		var left = box.outerWidth() > win.width() ? 20 : win.width() / 2 - box.outerWidth() / 2;
		var top = box.outerHeight() > win.height() ? 20 : win.height() / 2 - box.outerHeight() / 2;
		box.css({'left': left, 'top': 80});


		win.scrollTop(0);
		win.scrollLeft(0);
	};

	//Show the popup
	this.show = function(callback) {
		self.attachListeners();

		backdrop.fadeIn(function() {
			box.fadeIn(callback);
		});

		$(window).scrollTop(0);
		$(window).scrollLeft(0);

		return this;
	};

	//Hide the popup
	this.hide = function(callback) {
		if(returnSet){
			this.setContent(returnFiller);
		}
		else{
		box.fadeOut(function() {

			self.detachListeners();

			if($('body').children('.popup:visible').length === 0) {
				backdrop.fadeOut(function() {

					if(callback !== undefined && callback !== null)
						callback();
				});
			} else {
				if(callback !== undefined && callback !== null)
					callback();
			}
		});

		return this;
	}
	};

	this.returnToThis = function(){
		returnSet = true;
		return this;
	}

	this.noReturnToThis = function(){
		returnSet = false;
		return this;
	}

	//Sets the content that appears in the box. If the popup is visible the new size and position is calculated for animating.
	this.setContent = function(html, callback) {
		if(returnSet){
			returnFiller = box.html();
		}
		content = html;

		var contentArea = box.children().eq(0);
		var contentDiv = $('<div/>').html(content).hide();

		$('body').append(contentDiv);

		var width = contentDiv.outerWidth();
		var height = contentDiv.outerHeight();
		var win = $(window);
		var left = width > win.width() ? 20 : win.width() / 2 - width / 2;
		var top = height > win.height() ? 20 : win.height() / 2 - height / 2;

		if(contentArea.length === 0) {
			box.append(contentDiv).css({'left': left, 'top': 80});
			box.children().eq(0);/*css({'width': width, 'height': height});*/
			contentDiv.show();
			if(callback !== undefined && callback !== null)
				callback();
		} else {

			//$('body').remove(contentDiv);

			contentArea.animate({'opacity': 0.01}, function() {

				box.stop().animate({'left': left, 'top': 80});

				contentArea.stop().animate({'width': width, 'height': height}, function() {
					box.empty().append(contentDiv);
					contentDiv.fadeIn(callback);
				});

			});

		}


		return this;

	};

	//Shows a progress bar - no longer used
	this.showProgressBar = function(callback) {

		//$(window).on('resize', centerBox);
		var progressText = $('<div/>').html('<strong style="width: 100%;">Loading...</strong>');
		var progress = $('<div/>').append([progressText, $('<div/>').addClass('progressbar')]);
		self.setContent(progress.html());
		//centerBox();

		self.show(callback);

		return this;

	};

	//Attach the resize listener
	this.attachListeners = function() {
		if(!listeners) {
			listeners = true;
			$(window).on('resize', centerBox);

		}
	};

	//Remove the resize listener
	this.detachListeners = function() {

		if(listeners) {
			$(window).off('resize', centerBox);
			listeners = false;
		}

	};



};