function music_on(music_on) {
	if (music_on == true) {
		javascript: visNew('music_form');
		var myVideo = document.getElementsByTagName('video')[0];
		myVideo.play();
	} else {
		if (!myVideo.paused)
			myVideo.pause();
	}
}
