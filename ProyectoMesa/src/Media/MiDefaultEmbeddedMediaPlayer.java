//package Media;
//
//
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Window;
//import java.awt.image.BufferedImage;
//import java.awt.image.RenderedImage;
//import java.io.File;
//import java.util.List;
//
//import com.sun.jna.Pointer;
//
//import uk.co.caprica.vlcj.binding.LibVlc;
//import uk.co.caprica.vlcj.binding.internal.libvlc_audio_drain_cb;
//import uk.co.caprica.vlcj.binding.internal.libvlc_audio_flush_cb;
//import uk.co.caprica.vlcj.binding.internal.libvlc_audio_pause_cb;
//import uk.co.caprica.vlcj.binding.internal.libvlc_audio_play_cb;
//import uk.co.caprica.vlcj.binding.internal.libvlc_audio_resume_cb;
//import uk.co.caprica.vlcj.binding.internal.libvlc_instance_t;
//import uk.co.caprica.vlcj.binding.internal.libvlc_logo_position_e;
//import uk.co.caprica.vlcj.binding.internal.libvlc_marquee_position_e;
//import uk.co.caprica.vlcj.binding.internal.libvlc_media_parse_flag_t;
//import uk.co.caprica.vlcj.binding.internal.libvlc_media_stats_t;
//import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
//import uk.co.caprica.vlcj.binding.internal.libvlc_media_type_e;
//import uk.co.caprica.vlcj.binding.internal.libvlc_position_e;
//import uk.co.caprica.vlcj.binding.internal.libvlc_state_t;
//import uk.co.caprica.vlcj.binding.internal.libvlc_track_type_t;
//import uk.co.caprica.vlcj.medialist.MediaList;
//import uk.co.caprica.vlcj.player.AudioDevice;
//import uk.co.caprica.vlcj.player.ChapterDescription;
//import uk.co.caprica.vlcj.player.DeinterlaceMode;
//import uk.co.caprica.vlcj.player.Equalizer;
//import uk.co.caprica.vlcj.player.Logo;
//import uk.co.caprica.vlcj.player.Marquee;
//import uk.co.caprica.vlcj.player.MediaDetails;
//import uk.co.caprica.vlcj.player.MediaMeta;
//import uk.co.caprica.vlcj.player.MediaMetaData;
//import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
//import uk.co.caprica.vlcj.player.TitleDescription;
//import uk.co.caprica.vlcj.player.TrackDescription;
//import uk.co.caprica.vlcj.player.TrackInfo;
//import uk.co.caprica.vlcj.player.TrackType;
//import uk.co.caprica.vlcj.player.directaudio.AudioCallback;
//import uk.co.caprica.vlcj.player.embedded.DefaultEmbeddedMediaPlayer;
//import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
//import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
//import uk.co.caprica.vlcj.player.media.Media;
//
//public class MiDefaultEmbeddedMediaPlayer extends DefaultEmbeddedMediaPlayer {
//
//    public MiDefaultEmbeddedMediaPlayer(LibVlc libvlc, libvlc_instance_t instance) {
//        this(libvlc, instance, null);
//    }
//    public MiDefaultEmbeddedMediaPlayer(LibVlc libvlc, libvlc_instance_t instance, FullScreenStrategy fullScreenStrategy) {
//        super(libvlc, instance, fullScreenStrategy );
//        audioCallback = null;
//        this.playCallback = null;
//        this.pauseCallback = null;
//        this.resumeCallback = null;
//        this.flushCallback = null;
//        this.drainCallback = null;
//    }
//    public MiDefaultEmbeddedMediaPlayer(LibVlc libvlc, libvlc_instance_t instance, FullScreenStrategy fullScreenStrategy, AudioCallback audioCallback) {
//        super(libvlc, instance, fullScreenStrategy );
//        this.audioCallback = audioCallback;
//        this.playCallback = new PlayCallback();
//        this.pauseCallback = new PauseCallback();
//        this.resumeCallback = new ResumeCallback();
//        this.flushCallback = new FlushCallback();
//        this.drainCallback = new DrainCallback();
//        libvlc.libvlc_audio_set_callbacks(mediaPlayerInstance(), playCallback, pauseCallback, resumeCallback, flushCallback, drainCallback, null);
//    }
//	
//	@Override
//	public void setVideoSurface(CanvasVideoSurface videoSurface) {
//		// TODO Auto-generated method stub
//		super.setVideoSurface(videoSurface);
//	}
//
//	@Override
//	public void attachVideoSurface() {
//		// TODO Auto-generated method stub
//		super.attachVideoSurface();
//	}
//
//	@Override
//	public void setFullScreenStrategy(FullScreenStrategy fullScreenStrategy) {
//		// TODO Auto-generated method stub
//		super.setFullScreenStrategy(fullScreenStrategy);
//	}
//
//	@Override
//	public void toggleFullScreen() {
//		// TODO Auto-generated method stub
//		super.toggleFullScreen();
//	}
//
//	@Override
//	public void setFullScreen(boolean fullScreen) {
//		// TODO Auto-generated method stub
//		super.setFullScreen(fullScreen);
//	}
//
//	@Override
//	public boolean isFullScreen() {
//		// TODO Auto-generated method stub
//		return super.isFullScreen();
//	}
//
//	@Override
//	public BufferedImage getVideoSurfaceContents() {
//		// TODO Auto-generated method stub
//		return super.getVideoSurfaceContents();
//	}
//
//	@Override
//	public Window getOverlay() {
//		// TODO Auto-generated method stub
//		return super.getOverlay();
//	}
//
//	@Override
//	public void setOverlay(Window overlay) {
//		// TODO Auto-generated method stub
//		super.setOverlay(overlay);
//	}
//
//	@Override
//	public void enableOverlay(boolean enable) {
//		// TODO Auto-generated method stub
//		super.enableOverlay(enable);
//	}
//
//	@Override
//	public boolean overlayEnabled() {
//		// TODO Auto-generated method stub
//		return super.overlayEnabled();
//	}
//
//	@Override
//	public void setEnableMouseInputHandling(boolean enable) {
//		// TODO Auto-generated method stub
//		super.setEnableMouseInputHandling(enable);
//	}
//
//	@Override
//	public void setEnableKeyInputHandling(boolean enable) {
//		// TODO Auto-generated method stub
//		super.setEnableKeyInputHandling(enable);
//	}
//
//	@Override
//	public void addMediaPlayerEventListener(MediaPlayerEventListener listener) {
//		// TODO Auto-generated method stub
//		super.addMediaPlayerEventListener(listener);
//	}
//
//	@Override
//	public void removeMediaPlayerEventListener(MediaPlayerEventListener listener) {
//		// TODO Auto-generated method stub
//		super.removeMediaPlayerEventListener(listener);
//	}
//
//	@Override
//	public void enableEvents(long eventMask) {
//		// TODO Auto-generated method stub
//		super.enableEvents(eventMask);
//	}
//
//	@Override
//	public void setStandardMediaOptions(String... options) {
//		// TODO Auto-generated method stub
//		super.setStandardMediaOptions(options);
//	}
//
//	@Override
//	public boolean playMedia(String mrl, String... mediaOptions) {
//		// TODO Auto-generated method stub
//		return super.playMedia(mrl, mediaOptions);
//	}
//
//	@Override
//	public boolean playMedia(Media media) {
//		// TODO Auto-generated method stub
//		return super.playMedia(media);
//	}
//
//	@Override
//	public boolean prepareMedia(String mrl, String... mediaOptions) {
//		// TODO Auto-generated method stub
//		return super.prepareMedia(mrl, mediaOptions);
//	}
//
//	@Override
//	public boolean prepareMedia(Media media) {
//		// TODO Auto-generated method stub
//		return super.prepareMedia(media);
//	}
//
//	@Override
//	public boolean startMedia(String mrl, String... mediaOptions) {
//		// TODO Auto-generated method stub
//		return super.startMedia(mrl, mediaOptions);
//	}
//
//	@Override
//	public boolean startMedia(Media media) {
//		// TODO Auto-generated method stub
//		return super.startMedia(media);
//	}
//
//	@Override
//	public void parseMedia() {
//		// TODO Auto-generated method stub
//		super.parseMedia();
//	}
//
//	@Override
//	public void requestParseMedia() {
//		// TODO Auto-generated method stub
//		super.requestParseMedia();
//	}
//
//	@Override
//	public boolean requestParseMediaWithOptions(
//			libvlc_media_parse_flag_t... options) {
//		// TODO Auto-generated method stub
//		return super.requestParseMediaWithOptions(options);
//	}
//
//	@Override
//	public boolean isMediaParsed() {
//		// TODO Auto-generated method stub
//		return super.isMediaParsed();
//	}
//
//	@Override
//	public MediaMeta getMediaMeta() {
//		// TODO Auto-generated method stub
//		return super.getMediaMeta();
//	}
//
//	@Override
//	public MediaMeta getMediaMeta(libvlc_media_t media) {
//		// TODO Auto-generated method stub
//		return super.getMediaMeta(media);
//	}
//
//	@Override
//	public List<MediaMeta> getSubItemMediaMeta() {
//		// TODO Auto-generated method stub
//		return super.getSubItemMediaMeta();
//	}
//
//	@Override
//	public MediaMetaData getMediaMetaData() {
//		// TODO Auto-generated method stub
//		return super.getMediaMetaData();
//	}
//
//	@Override
//	public List<MediaMetaData> getSubItemMediaMetaData() {
//		// TODO Auto-generated method stub
//		return super.getSubItemMediaMetaData();
//	}
//
//	@Override
//	public void addMediaOptions(String... mediaOptions) {
//		// TODO Auto-generated method stub
//		super.addMediaOptions(mediaOptions);
//	}
//
//	@Override
//	public void setRepeat(boolean repeat) {
//		// TODO Auto-generated method stub
//		super.setRepeat(repeat);
//	}
//
//	@Override
//	public boolean getRepeat() {
//		// TODO Auto-generated method stub
//		return super.getRepeat();
//	}
//
//	@Override
//	public void setPlaySubItems(boolean playSubItems) {
//		// TODO Auto-generated method stub
//		super.setPlaySubItems(playSubItems);
//	}
//
//	@Override
//	public int subItemCount() {
//		// TODO Auto-generated method stub
//		return super.subItemCount();
//	}
//
//	@Override
//	public int subItemIndex() {
//		// TODO Auto-generated method stub
//		return super.subItemIndex();
//	}
//
//	@Override
//	public List<String> subItems() {
//		// TODO Auto-generated method stub
//		return super.subItems();
//	}
//
//	@Override
//	public List<libvlc_media_t> subItemsMedia() {
//		// TODO Auto-generated method stub
//		return super.subItemsMedia();
//	}
//
//	@Override
//	public MediaList subItemsMediaList() {
//		// TODO Auto-generated method stub
//		return super.subItemsMediaList();
//	}
//
//	@Override
//	public boolean playNextSubItem(String... mediaOptions) {
//		// TODO Auto-generated method stub
//		return super.playNextSubItem(mediaOptions);
//	}
//
//	@Override
//	public boolean playSubItem(int index, String... mediaOptions) {
//		// TODO Auto-generated method stub
//		return super.playSubItem(index, mediaOptions);
//	}
//
//	@Override
//	public boolean isPlayable() {
//		// TODO Auto-generated method stub
//		return super.isPlayable();
//	}
//
//	@Override
//	public boolean isPlaying() {
//		// TODO Auto-generated method stub
//		return super.isPlaying();
//	}
//
//	@Override
//	public boolean isSeekable() {
//		// TODO Auto-generated method stub
//		return super.isSeekable();
//	}
//
//	@Override
//	public boolean canPause() {
//		// TODO Auto-generated method stub
//		return super.canPause();
//	}
//
//	@Override
//	public boolean programScrambled() {
//		// TODO Auto-generated method stub
//		return super.programScrambled();
//	}
//
//	@Override
//	public long getLength() {
//		// TODO Auto-generated method stub
//		return super.getLength();
//	}
//
//	@Override
//	public long getTime() {
//		// TODO Auto-generated method stub
//		return super.getTime();
//	}
//
//	@Override
//	public float getPosition() {
//		// TODO Auto-generated method stub
//		return super.getPosition();
//	}
//
//	@Override
//	public float getFps() {
//		// TODO Auto-generated method stub
//		return super.getFps();
//	}
//
//	@Override
//	public float getRate() {
//		// TODO Auto-generated method stub
//		return super.getRate();
//	}
//
//	@Override
//	public int getVideoOutputs() {
//		// TODO Auto-generated method stub
//		return super.getVideoOutputs();
//	}
//
//	@Override
//	public Dimension getVideoDimension() {
//		// TODO Auto-generated method stub
//		return super.getVideoDimension();
//	}
//
//	@Override
//	public MediaDetails getMediaDetails() {
//		// TODO Auto-generated method stub
//		return super.getMediaDetails();
//	}
//
//	@Override
//	public String getAspectRatio() {
//		// TODO Auto-generated method stub
//		return super.getAspectRatio();
//	}
//
//	@Override
//	public float getScale() {
//		// TODO Auto-generated method stub
//		return super.getScale();
//	}
//
//	@Override
//	public String getCropGeometry() {
//		// TODO Auto-generated method stub
//		return super.getCropGeometry();
//	}
//
//	@Override
//	public libvlc_media_stats_t getMediaStatistics() {
//		// TODO Auto-generated method stub
//		return super.getMediaStatistics();
//	}
//
//	@Override
//	public libvlc_media_stats_t getMediaStatistics(libvlc_media_t media) {
//		// TODO Auto-generated method stub
//		return super.getMediaStatistics(media);
//	}
//
//	@Override
//	public libvlc_state_t getMediaState() {
//		// TODO Auto-generated method stub
//		return super.getMediaState();
//	}
//
//	@Override
//	public libvlc_state_t getMediaPlayerState() {
//		// TODO Auto-generated method stub
//		return super.getMediaPlayerState();
//	}
//
//	@Override
//	public int getTitleCount() {
//		// TODO Auto-generated method stub
//		return super.getTitleCount();
//	}
//
//	@Override
//	public int getTitle() {
//		// TODO Auto-generated method stub
//		return super.getTitle();
//	}
//
//	@Override
//	public void setTitle(int title) {
//		// TODO Auto-generated method stub
//		super.setTitle(title);
//	}
//
//	@Override
//	public int getVideoTrackCount() {
//		// TODO Auto-generated method stub
//		return super.getVideoTrackCount();
//	}
//
//	@Override
//	public int getVideoTrack() {
//		// TODO Auto-generated method stub
//		return super.getVideoTrack();
//	}
//
//	@Override
//	public int setVideoTrack(int track) {
//		// TODO Auto-generated method stub
//		return super.setVideoTrack(track);
//	}
//
//	@Override
//	public int getAudioTrackCount() {
//		// TODO Auto-generated method stub
//		return super.getAudioTrackCount();
//	}
//
//	@Override
//	public int getAudioTrack() {
//		// TODO Auto-generated method stub
//		return super.getAudioTrack();
//	}
//
//	@Override
//	public int setAudioTrack(int track) {
//		// TODO Auto-generated method stub
//		return super.setAudioTrack(track);
//	}
//
//	@Override
//	public void play() {
//		// TODO Auto-generated method stub
//		super.play();
//	}
//
//	@Override
//	public boolean start() {
//		// TODO Auto-generated method stub
//		return super.start();
//	}
//
//	@Override
//	public void stop() {
//		// TODO Auto-generated method stub
//		super.stop();
//	}
//
//	@Override
//	public void setPause(boolean pause) {
//		// TODO Auto-generated method stub
//		super.setPause(pause);
//	}
//
//	@Override
//	public void pause() {
//		// TODO Auto-generated method stub
//		super.pause();
//	}
//
//	@Override
//	public void nextFrame() {
//		// TODO Auto-generated method stub
//		super.nextFrame();
//	}
//
//	@Override
//	public void skip(long delta) {
//		// TODO Auto-generated method stub
//		super.skip(delta);
//	}
//
//	@Override
//	public void skipPosition(float delta) {
//		// TODO Auto-generated method stub
//		super.skipPosition(delta);
//	}
//
//	@Override
//	public void setTime(long time) {
//		// TODO Auto-generated method stub
//		super.setTime(time);
//	}
//
//	@Override
//	public void setPosition(float position) {
//		// TODO Auto-generated method stub
//		super.setPosition(position);
//	}
//
//	@Override
//	public int setRate(float rate) {
//		// TODO Auto-generated method stub
//		return super.setRate(rate);
//	}
//
//	@Override
//	public void setAspectRatio(String aspectRatio) {
//		// TODO Auto-generated method stub
//		super.setAspectRatio(aspectRatio);
//	}
//
//	@Override
//	public void setScale(float factor) {
//		// TODO Auto-generated method stub
//		super.setScale(factor);
//	}
//
//	@Override
//	public void setCropGeometry(String cropGeometry) {
//		// TODO Auto-generated method stub
//		super.setCropGeometry(cropGeometry);
//	}
//
//	@Override
//	public boolean setAudioOutput(String output) {
//		// TODO Auto-generated method stub
//		return super.setAudioOutput(output);
//	}
//
//	@Override
//	public void setAudioOutputDevice(String output, String outputDeviceId) {
//		// TODO Auto-generated method stub
//		super.setAudioOutputDevice(output, outputDeviceId);
//	}
//
//	@Override
//	public String getAudioOutputDevice() {
//		// TODO Auto-generated method stub
//		return super.getAudioOutputDevice();
//	}
//
//	@Override
//	public List<AudioDevice> getAudioOutputDevices() {
//		// TODO Auto-generated method stub
//		return super.getAudioOutputDevices();
//	}
//
//	@Override
//	public boolean mute() {
//		// TODO Auto-generated method stub
//		return super.mute();
//	}
//
//	@Override
//	public void mute(boolean mute) {
//		// TODO Auto-generated method stub
//		super.mute(mute);
//	}
//
//	@Override
//	public boolean isMute() {
//		// TODO Auto-generated method stub
//		return super.isMute();
//	}
//
//	@Override
//	public int getVolume() {
//		// TODO Auto-generated method stub
//		return super.getVolume();
//	}
//
//	@Override
//	public void setVolume(int volume) {
//		// TODO Auto-generated method stub
//		super.setVolume(volume);
//	}
//
//	@Override
//	public int getAudioChannel() {
//		// TODO Auto-generated method stub
//		return super.getAudioChannel();
//	}
//
//	@Override
//	public void setAudioChannel(int channel) {
//		// TODO Auto-generated method stub
//		super.setAudioChannel(channel);
//	}
//
//	@Override
//	public long getAudioDelay() {
//		// TODO Auto-generated method stub
//		return super.getAudioDelay();
//	}
//
//	@Override
//	public void setAudioDelay(long delay) {
//		// TODO Auto-generated method stub
//		super.setAudioDelay(delay);
//	}
//
//	@Override
//	public int getChapterCount() {
//		// TODO Auto-generated method stub
//		return super.getChapterCount();
//	}
//
//	@Override
//	public int getChapter() {
//		// TODO Auto-generated method stub
//		return super.getChapter();
//	}
//
//	@Override
//	public void setChapter(int chapterNumber) {
//		// TODO Auto-generated method stub
//		super.setChapter(chapterNumber);
//	}
//
//	@Override
//	public void nextChapter() {
//		// TODO Auto-generated method stub
//		super.nextChapter();
//	}
//
//	@Override
//	public void previousChapter() {
//		// TODO Auto-generated method stub
//		super.previousChapter();
//	}
//
//	@Override
//	public void menuActivate() {
//		// TODO Auto-generated method stub
//		super.menuActivate();
//	}
//
//	@Override
//	public void menuUp() {
//		// TODO Auto-generated method stub
//		super.menuUp();
//	}
//
//	@Override
//	public void menuDown() {
//		// TODO Auto-generated method stub
//		super.menuDown();
//	}
//
//	@Override
//	public void menuLeft() {
//		// TODO Auto-generated method stub
//		super.menuLeft();
//	}
//
//	@Override
//	public void menuRight() {
//		// TODO Auto-generated method stub
//		super.menuRight();
//	}
//
//	@Override
//	public int getSpuCount() {
//		// TODO Auto-generated method stub
//		return super.getSpuCount();
//	}
//
//	@Override
//	public int getSpu() {
//		// TODO Auto-generated method stub
//		return super.getSpu();
//	}
//
//	@Override
//	public int setSpu(int spu) {
//		// TODO Auto-generated method stub
//		return super.setSpu(spu);
//	}
//
//	@Override
//	public long getSpuDelay() {
//		// TODO Auto-generated method stub
//		return super.getSpuDelay();
//	}
//
//	@Override
//	public void setSpuDelay(long delay) {
//		// TODO Auto-generated method stub
//		super.setSpuDelay(delay);
//	}
//
//	@Override
//	public void setSubTitleFile(String subTitleFileName) {
//		// TODO Auto-generated method stub
//		super.setSubTitleFile(subTitleFileName);
//	}
//
//	@Override
//	public void setSubTitleFile(File subTitleFile) {
//		// TODO Auto-generated method stub
//		super.setSubTitleFile(subTitleFile);
//	}
//
//	@Override
//	public int getTeletextPage() {
//		// TODO Auto-generated method stub
//		return super.getTeletextPage();
//	}
//
//	@Override
//	public void setTeletextPage(int pageNumber) {
//		// TODO Auto-generated method stub
//		super.setTeletextPage(pageNumber);
//	}
//
//	@Override
//	public void toggleTeletext() {
//		// TODO Auto-generated method stub
//		super.toggleTeletext();
//	}
//
//	@Override
//	public List<TrackDescription> getTitleDescriptions() {
//		// TODO Auto-generated method stub
//		return super.getTitleDescriptions();
//	}
//
//	@Override
//	public List<TrackDescription> getVideoDescriptions() {
//		// TODO Auto-generated method stub
//		return super.getVideoDescriptions();
//	}
//
//	@Override
//	public List<TrackDescription> getAudioDescriptions() {
//		// TODO Auto-generated method stub
//		return super.getAudioDescriptions();
//	}
//
//	@Override
//	public List<TrackDescription> getSpuDescriptions() {
//		// TODO Auto-generated method stub
//		return super.getSpuDescriptions();
//	}
//
//	@Override
//	public List<String> getChapterDescriptions(int title) {
//		// TODO Auto-generated method stub
//		return super.getChapterDescriptions(title);
//	}
//
//	@Override
//	public List<String> getChapterDescriptions() {
//		// TODO Auto-generated method stub
//		return super.getChapterDescriptions();
//	}
//
//	@Override
//	public List<List<String>> getAllChapterDescriptions() {
//		// TODO Auto-generated method stub
//		return super.getAllChapterDescriptions();
//	}
//
//	@Override
//	public List<TitleDescription> getExtendedTitleDescriptions() {
//		// TODO Auto-generated method stub
//		return super.getExtendedTitleDescriptions();
//	}
//
//	@Override
//	public List<ChapterDescription> getExtendedChapterDescriptions() {
//		// TODO Auto-generated method stub
//		return super.getExtendedChapterDescriptions();
//	}
//
//	@Override
//	public List<ChapterDescription> getExtendedChapterDescriptions(int title) {
//		// TODO Auto-generated method stub
//		return super.getExtendedChapterDescriptions(title);
//	}
//
//	@Override
//	public List<TrackInfo> getTrackInfo(TrackType... types) {
//		// TODO Auto-generated method stub
//		return super.getTrackInfo(types);
//	}
//
//	@Override
//	public List<TrackInfo> getTrackInfo(libvlc_media_t media,
//			TrackType... types) {
//		// TODO Auto-generated method stub
//		return super.getTrackInfo(media, types);
//	}
//
//	@Override
//	public libvlc_media_type_e getMediaType() {
//		// TODO Auto-generated method stub
//		return super.getMediaType();
//	}
//
//	@Override
//	public libvlc_media_type_e getMediaType(libvlc_media_t media) {
//		// TODO Auto-generated method stub
//		return super.getMediaType(media);
//	}
//
//	@Override
//	public String getCodecDescription(libvlc_track_type_t type, int codec) {
//		// TODO Auto-generated method stub
//		return super.getCodecDescription(type, codec);
//	}
//
//	@Override
//	public List<List<TrackInfo>> getSubItemTrackInfo(TrackType... types) {
//		// TODO Auto-generated method stub
//		return super.getSubItemTrackInfo(types);
//	}
//
//	@Override
//	public void setSnapshotDirectory(String snapshotDirectoryName) {
//		// TODO Auto-generated method stub
//		super.setSnapshotDirectory(snapshotDirectoryName);
//	}
//
//	@Override
//	public boolean saveSnapshot() {
//		// TODO Auto-generated method stub
//		return super.saveSnapshot();
//	}
//
//	@Override
//	public boolean saveSnapshot(int width, int height) {
//		// TODO Auto-generated method stub
//		return super.saveSnapshot(width, height);
//	}
//
//	@Override
//	public boolean saveSnapshot(File file) {
//		// TODO Auto-generated method stub
//		return super.saveSnapshot(file);
//	}
//
//	@Override
//	public boolean saveSnapshot(File file, int width, int height) {
//		// TODO Auto-generated method stub
//		return super.saveSnapshot(file, width, height);
//	}
//
//	@Override
//	public BufferedImage getSnapshot() {
//		// TODO Auto-generated method stub
//		return super.getSnapshot();
//	}
//
//	@Override
//	public BufferedImage getSnapshot(int width, int height) {
//		// TODO Auto-generated method stub
//		return super.getSnapshot(width, height);
//	}
//
//	@Override
//	public void enableLogo(boolean enable) {
//		// TODO Auto-generated method stub
//		super.enableLogo(enable);
//	}
//
//	@Override
//	public void setLogoOpacity(int opacity) {
//		// TODO Auto-generated method stub
//		super.setLogoOpacity(opacity);
//	}
//
//	@Override
//	public void setLogoOpacity(float opacity) {
//		// TODO Auto-generated method stub
//		super.setLogoOpacity(opacity);
//	}
//
//	@Override
//	public void setLogoLocation(int x, int y) {
//		// TODO Auto-generated method stub
//		super.setLogoLocation(x, y);
//	}
//
//	@Override
//	public void setLogoPosition(libvlc_logo_position_e position) {
//		// TODO Auto-generated method stub
//		super.setLogoPosition(position);
//	}
//
//	@Override
//	public void setLogoFile(String logoFile) {
//		// TODO Auto-generated method stub
//		super.setLogoFile(logoFile);
//	}
//
//	@Override
//	public void setLogoImage(RenderedImage logoImage) {
//		// TODO Auto-generated method stub
//		super.setLogoImage(logoImage);
//	}
//
//	@Override
//	public void setLogo(Logo logo) {
//		// TODO Auto-generated method stub
//		super.setLogo(logo);
//	}
//
//	@Override
//	public void enableMarquee(boolean enable) {
//		// TODO Auto-generated method stub
//		super.enableMarquee(enable);
//	}
//
//	@Override
//	public void setMarqueeText(String text) {
//		// TODO Auto-generated method stub
//		super.setMarqueeText(text);
//	}
//
//	@Override
//	public void setMarqueeColour(Color colour) {
//		// TODO Auto-generated method stub
//		super.setMarqueeColour(colour);
//	}
//
//	@Override
//	public void setMarqueeColour(int colour) {
//		// TODO Auto-generated method stub
//		super.setMarqueeColour(colour);
//	}
//
//	@Override
//	public void setMarqueeOpacity(int opacity) {
//		// TODO Auto-generated method stub
//		super.setMarqueeOpacity(opacity);
//	}
//
//	@Override
//	public void setMarqueeOpacity(float opacity) {
//		// TODO Auto-generated method stub
//		super.setMarqueeOpacity(opacity);
//	}
//
//	@Override
//	public void setMarqueeSize(int size) {
//		// TODO Auto-generated method stub
//		super.setMarqueeSize(size);
//	}
//
//	@Override
//	public void setMarqueeTimeout(int timeout) {
//		// TODO Auto-generated method stub
//		super.setMarqueeTimeout(timeout);
//	}
//
//	@Override
//	public void setMarqueeLocation(int x, int y) {
//		// TODO Auto-generated method stub
//		super.setMarqueeLocation(x, y);
//	}
//
//	@Override
//	public void setMarqueePosition(libvlc_marquee_position_e position) {
//		// TODO Auto-generated method stub
//		super.setMarqueePosition(position);
//	}
//
//	@Override
//	public void setMarquee(Marquee marquee) {
//		// TODO Auto-generated method stub
//		super.setMarquee(marquee);
//	}
//
//	@Override
//	public void setDeinterlace(DeinterlaceMode deinterlaceMode) {
//		// TODO Auto-generated method stub
//		super.setDeinterlace(deinterlaceMode);
//	}
//
//	@Override
//	public void setAdjustVideo(boolean adjustVideo) {
//		// TODO Auto-generated method stub
//		super.setAdjustVideo(adjustVideo);
//	}
//
//	@Override
//	public boolean isAdjustVideo() {
//		// TODO Auto-generated method stub
//		return super.isAdjustVideo();
//	}
//
//	@Override
//	public float getContrast() {
//		// TODO Auto-generated method stub
//		return super.getContrast();
//	}
//
//	@Override
//	public void setContrast(float contrast) {
//		// TODO Auto-generated method stub
//		super.setContrast(contrast);
//	}
//
//	@Override
//	public float getBrightness() {
//		// TODO Auto-generated method stub
//		return super.getBrightness();
//	}
//
//	@Override
//	public void setBrightness(float brightness) {
//		// TODO Auto-generated method stub
//		super.setBrightness(brightness);
//	}
//
//	@Override
//	public int getHue() {
//		// TODO Auto-generated method stub
//		return super.getHue();
//	}
//
//	@Override
//	public void setHue(int hue) {
//		// TODO Auto-generated method stub
//		super.setHue(hue);
//	}
//
//	@Override
//	public float getSaturation() {
//		// TODO Auto-generated method stub
//		return super.getSaturation();
//	}
//
//	@Override
//	public void setSaturation(float saturation) {
//		// TODO Auto-generated method stub
//		super.setSaturation(saturation);
//	}
//
//	@Override
//	public float getGamma() {
//		// TODO Auto-generated method stub
//		return super.getGamma();
//	}
//
//	@Override
//	public void setGamma(float gamma) {
//		// TODO Auto-generated method stub
//		super.setGamma(gamma);
//	}
//
//	@Override
//	public void setVideoTitleDisplay(libvlc_position_e position, int timeout) {
//		// TODO Auto-generated method stub
//		super.setVideoTitleDisplay(position, timeout);
//	}
//
//	@Override
//	public Equalizer getEqualizer() {
//		// TODO Auto-generated method stub
//		return super.getEqualizer();
//	}
//
//	@Override
//	public void setEqualizer(Equalizer equalizer) {
//		// TODO Auto-generated method stub
//		super.setEqualizer(equalizer);
//	}
//
//	@Override
//	public String mrl() {
//		// TODO Auto-generated method stub
//		return super.mrl();
//	}
//
//	@Override
//	public String mrl(libvlc_media_t mediaInstance) {
//		// TODO Auto-generated method stub
//		return super.mrl(mediaInstance);
//	}
//
//	@Override
//	public Object userData() {
//		// TODO Auto-generated method stub
//		return super.userData();
//	}
//
//	@Override
//	public void userData(Object userData) {
//		// TODO Auto-generated method stub
//		super.userData(userData);
//	}
//
//	@Override
//	protected void onAfterRelease() {
//		// TODO Auto-generated method stub
//		super.onAfterRelease();
//	}
//
//	@Override
//	protected void finalize() throws Throwable {
//		// TODO Auto-generated method stub
//		super.finalize();
//	}
//
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		// TODO Auto-generated method stub
//		return super.clone();
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		return super.equals(obj);
//	}
//
//	@Override
//	public int hashCode() {
//		// TODO Auto-generated method stub
//		return super.hashCode();
//	}
//
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return super.toString();
//	}
//
//
//    /**
//     * Implementation of a callback invoked by the native library to play a series of samples.
//     */
//    private final class PlayCallback implements libvlc_audio_play_cb {
//        @Override
//        public void play(Pointer data, Pointer samples, int count, long pts) {
//            if (audioCallback!=null) audioCallback.play(null, samples, count, pts);
//        }
//    }
//
//    /**
//     * Implementation of a callback invoked by the native library when audio is paused.
//     */
//    private final class PauseCallback implements libvlc_audio_pause_cb {
//        @Override
//        public void pause(Pointer data, long pts) {
//            System.out.println( "pause(pts={}) " + pts);
//            if (audioCallback!=null) audioCallback.pause(null, pts);
//        }
//    }
//
//    /**
//     * Implementation of a callback invoked by the native library when audio is resumed.
//     */
//    private final class ResumeCallback implements libvlc_audio_resume_cb {
//        @Override
//        public void resume(Pointer data, long pts) {
//            System.out.println( "resume(pts={}) " + pts);
//            if (audioCallback!=null) audioCallback.resume(null, pts);
//        }
//    }
//
//    /**
//     * Implementation of a callback invoked by the native library when audio is flushed.
//     */
//    private final class FlushCallback implements libvlc_audio_flush_cb {
//        @Override
//        public void flush(Pointer data, long pts) {
//            System.out.println( "flush(pts={}) " + pts);
//            if (audioCallback!=null) audioCallback.flush(null, pts);
//        }
//    }
//
//    /**
//     * Implementation of a callback invoked by the native library when audio is drained.
//     */
//    private final class DrainCallback implements libvlc_audio_drain_cb {
//        @Override
//        public void drain(Pointer data) {
//            System.out.println( "drain()");
//            if (audioCallback!=null) audioCallback.drain(null);
//        }
//    }
//	
//    /**
//     * Audio callback receives native callback events.
//     */
//    private final AudioCallback audioCallback;
//    /**
//     * Play callback.
//     * <p>
//     * A hard reference to the callback must be kept otherwise the callback will get garbage
//     * collected and cause a native crash.
//     */
//    private final libvlc_audio_play_cb playCallback;
//
//    /**
//     * Pause callback.
//     * <p>
//     * A hard reference to the callback must be kept otherwise the callback will get garbage
//     * collected and cause a native crash.
//     */
//    private final libvlc_audio_pause_cb pauseCallback;
//
//    /**
//     * Resume callback.
//     * <p>
//     * A hard reference to the callback must be kept otherwise the callback will get garbage
//     * collected and cause a native crash.
//     */
//    private final libvlc_audio_resume_cb resumeCallback;
//
//    /**
//     * Flush callback.
//     * <p>
//     * A hard reference to the callback must be kept otherwise the callback will get garbage
//     * collected and cause a native crash.
//     */
//    private final libvlc_audio_flush_cb flushCallback;
//
//    /**
//     * Drain callback.
//     * <p>
//     * A hard reference to the callback must be kept otherwise the callback will get garbage
//     * collected and cause a native crash.
//     */
//    private final libvlc_audio_drain_cb drainCallback;
//
//    
//}
