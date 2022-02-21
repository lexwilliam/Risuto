package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.remote.detail.*
import com.lexwilliam.domain.model.remote.detail.*
import javax.inject.Inject

interface DetailMapper {
    fun toDomain(anime: AnimeDetailRepo): AnimeDetail
    fun toDomain(charStaff: CharacterStaffRepo): CharacterStaff
    fun toDomain(episodes: EpisodesRepo): Episodes
    fun toDomain(forum: ForumRepo): Forum
    fun toDomain(moreInfo: MoreInfoRepo): MoreInfo
    fun toDomain(news: NewsRepo): News
    fun toDomain(pictures: PicturesRepo): Pictures
    fun toDomain(recommendations: RecommendationsRepo): Recommendations
    fun toDomain(reviews: ReviewsRepo): Reviews
    fun toDomain(stats: StatsRepo): Stats
    fun toDomain(videos: VideosRepo): Videos
    fun toDomain(status: MyAnimeStatusRepo): MyAnimeStatus
}

class DetailMapperImpl @Inject constructor(
    private val commonMapper: CommonMapper
): DetailMapper {
    override fun toDomain(anime: AnimeDetailRepo): AnimeDetail =
        AnimeDetail(commonMapper.toDomain(anime.aired), anime.airing, anime.background, anime.broadcast, anime.duration, anime.ending_themes, anime.episodes, anime.favorites, anime.genres.map { commonMapper.toDomain(it) }, anime.image_url, anime.licensors.map { commonMapper.toDomain(it) }, anime.mal_id, anime.members, anime.opening_themes, anime.popularity, anime.premiered, anime.producers.map { commonMapper.toDomain(it) }, anime.rank, anime.rating, commonMapper.toDomain(anime.related), anime.request_cache_expiry, anime.request_cached, anime.request_hash, anime.score, anime.scored_by, anime.source, anime.status, anime.studios.map { commonMapper.toDomain(it) }, anime.synopsis, anime.title, anime.title_english, anime.title_japanese, anime.title_synonyms, anime.trailer_url, anime.type, anime.url)

    override fun toDomain(charStaff: CharacterStaffRepo): CharacterStaff =
        CharacterStaff(charStaff.characters?.map { toDomain(it) }, charStaff.request_cache_expiry, charStaff.request_cached, charStaff.request_hash, charStaff.staff?.map { toDomain(it) })

    override fun toDomain(episodes: EpisodesRepo): Episodes =
        Episodes(episodes.episodes.map { toDomain(it) }, episodes.episodes_last_page, episodes.request_cache_expiry, episodes.request_cached, episodes.request_hash)

    override fun toDomain(forum: ForumRepo): Forum =
        Forum(forum.request_cache_expiry, forum.request_cached, forum.request_hash, forum.topics.map { toDomain(it) })

    override fun toDomain(moreInfo: MoreInfoRepo): MoreInfo =
        MoreInfo(moreInfo.moreinfo, moreInfo.request_cache_expiry, moreInfo.request_cached, moreInfo.request_hash)

    override fun toDomain(news: NewsRepo): News =
        News(news.articles.map { toDomain(it) }, news.request_cache_expiry, news.request_cached, news.request_hash)

    override fun toDomain(pictures: PicturesRepo): Pictures =
        Pictures(pictures.pictures.map { toDomain(it) }, pictures.request_cache_expiry, pictures.request_cached, pictures.request_hash)

    override fun toDomain(recommendations: RecommendationsRepo): Recommendations =
        Recommendations(recommendations.recommendations.map { toDomain(it) }, recommendations.request_cache_expiry, recommendations.request_cached, recommendations.request_hash)

    override fun toDomain(reviews: ReviewsRepo): Reviews =
        Reviews(reviews.request_cache_expiry, reviews.request_cached, reviews.request_hash, reviews.reviews.map { toDomain(it) })

    override fun toDomain(stats: StatsRepo): Stats =
        Stats(stats.completed, stats.dropped, stats.on_hold, stats.plan_to_watch, stats.request_cache_expiry, stats.request_cached, stats.request_hash, toDomain(stats.scores), stats.total, stats.watching)

    override fun toDomain(videos: VideosRepo): Videos =
        Videos(videos.episodes, videos.promo.map { toDomain(it) }, videos.request_cache_expiry, videos.request_cached, videos.request_hash)

    override fun toDomain(status: MyAnimeStatusRepo): MyAnimeStatus =
        MyAnimeStatus(status.status, status.score, status.numEpisodesWatched, status.isRewatching, status.updatedAt)


    private fun toDomain(character: CharacterRepo): Character =
        Character(character.image_url, character.mal_id, character.name, character.role, character.url, character.voice_actors.map { toDomain(it) })

    private fun toDomain(voiceActor: VoiceActorRepo): VoiceActor =
        VoiceActor(voiceActor.image_url, voiceActor.language, voiceActor.mal_id, voiceActor.name, voiceActor.url)

    private fun toDomain(staff: StaffRepo): Staff =
        Staff(staff.image_url, staff.mal_id, staff.name, staff.positions, staff.url)

    private fun toDomain(episode: EpisodeRepo): Episode =
        Episode(episode.aired, episode.episode_id, episode.filler, episode.forum_url, episode.recap, episode.title, episode.title_japanese, episode.title_romanji, episode.video_url)

    private fun toDomain(topic: TopicRepo): Topic =
        Topic(topic.author_name, topic.author_url, topic.date_posted, toDomain(topic.last_post), topic.replies, topic.title, topic.topic_id, topic.url)

    private fun toDomain(lastPost: LastPostRepo): LastPost =
        LastPost(lastPost.author_name, lastPost.author_url, lastPost.date_posted, lastPost.url)

    private fun toDomain(article: ArticleRepo): Article =
        Article(article.author_name, article.author_url, article.comments, article.date, article.forum_url, article.image_url, article.intro, article.title, article.url)

    private fun toDomain(picture: PictureRepo): Picture =
        Picture(picture.large, picture.small)

    private fun toDomain(recommendation: RecommendationRepo): Recommendation =
        Recommendation(recommendation.image_url, recommendation.mal_id, recommendation.recommendation_count, recommendation.recommendation_url, recommendation.title, recommendation.url)

    private fun toDomain(review: ReviewRepo): Review =
        Review(review.content, review.date, review.helpful_count, review.mal_id, toDomain(review.reviewer), review.type, review.url)

    private fun toDomain(reviewer: ReviewerRepo): Reviewer =
        Reviewer(reviewer.episodes_seen, reviewer.image_url, toDomain(reviewer.reviewScore), reviewer.url, reviewer.username)

    private fun toDomain(score: ReviewScoreRepo): ReviewScore =
        ReviewScore(score.animation, score.character, score.enjoyment, score.overall, score.sound, score.story)

    private fun toDomain(promo: PromoRepo): Promo =
        Promo(promo.image_url, promo.title, promo.video_url)

}