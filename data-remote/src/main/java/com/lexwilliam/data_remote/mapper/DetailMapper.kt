package com.lexwilliam.data_remote.mapper

import com.lexwilliam.data.model.common.*
import com.lexwilliam.data.model.remote.detail.*
import com.lexwilliam.data_remote.model.common.*
import com.lexwilliam.data_remote.model.detail.*
import javax.inject.Inject

interface DetailMapper {
    fun toRepo(anime: AnimeDetailResponse): AnimeDetailRepo
    fun toRepo(charStaff: CharacterStaffResponse): CharacterStaffRepo
    fun toRepo(episodes: EpisodesResponse): EpisodesRepo
    fun toRepo(forum: ForumResponse): ForumRepo
    fun toRepo(moreInfo: MoreInfoResponse): MoreInfoRepo
    fun toRepo(news: NewsResponse): NewsRepo
    fun toRepo(pictures: PicturesResponse): PicturesRepo
    fun toRepo(recommendations: RecommendationsResponse): RecommendationsRepo
    fun toRepo(reviews: ReviewsResponse): ReviewsRepo
    fun toRepo(stats: StatsResponse): StatsRepo
    fun toRepo(videos: VideosResponse): VideosRepo
}

class DetailMapperImpl @Inject constructor(
    private val commonMapper: CommonMapper
): DetailMapper {
    override fun toRepo(anime: AnimeDetailResponse): AnimeDetailRepo =
        AnimeDetailRepo(
            commonMapper.toRepo(anime.aired?: AiredResponse("", PropResponse(FromResponse(0,0,0), ToResponse(0,0,0)),"", "")),
            anime.airing?: false,
            anime.background?: "",
            anime.broadcast?: "",
            anime.duration?: "",
            anime.ending_themes?:listOf(""),
            anime.episodes?:0,
            anime.favorites?:0,
            anime.genres?.map { commonMapper.toRepo(it) } ?: listOf(GenreRepo(0, "", "", "")),
            anime.image_url?:"",
            anime.licensors?.map { commonMapper.toRepo(it) } ?: listOf(LicensorRepo(0, "", "", "")),
            anime.mal_id?:0,
            anime.members?:0,
            anime.opening_themes?:listOf(""),
            anime.popularity?:0,
            anime.premiered?:"",
            anime.producers?.map { commonMapper.toRepo(it) } ?: listOf(ProducerRepo(0, "", "", "")),
            anime.rank?:0,
            anime.rating?:"",
            commonMapper.toRepo(anime.related?: RelatedResponse()),
            anime.request_cache_expiry?:0,
            anime.request_cached?:false,
            anime.request_hash?:"",
            anime.score?:0.0,
            anime.scored_by?:0,
            anime.source?:"",
            anime.status?:"",
            anime.studios?.map { commonMapper.toRepo(it) } ?: listOf(StudioRepo(0, "", "", "")),
            anime.synopsis?:"",
            anime.title?:"",
            anime.title_english?:"",
            anime.title_japanese?:"",
            anime.title_synonyms?:listOf(""),
            anime.trailer_url?:"",
            anime.type?:"",
            anime.url?:""
        )

    override fun toRepo(charStaff: CharacterStaffResponse): CharacterStaffRepo =
        CharacterStaffRepo(charStaff.characters?.map { toRepo(it) }, charStaff.request_cache_expiry, charStaff.request_cached, charStaff.request_hash, charStaff.staff?.map { toRepo(it) })

    private fun toRepo(character: CharacterResponse): CharacterRepo =
        CharacterRepo(character.image_url, character.mal_id, character.name, character.role, character.url, character.voice_actors.map { toRepo(it) })

    private fun toRepo(voiceActor: VoiceActorResponse): VoiceActorRepo =
        VoiceActorRepo(voiceActor.image_url, voiceActor.language, voiceActor.mal_id, voiceActor.name, voiceActor.url)

    private fun toRepo(staff: StaffResponse): StaffRepo =
        StaffRepo(staff.image_url, staff.mal_id, staff.name, staff.positions, staff.url)

    override fun toRepo(episodes: EpisodesResponse): EpisodesRepo =
        EpisodesRepo(episodes.episodes.map { toRepo(it) }, episodes.episodes_last_page, episodes.request_cache_expiry, episodes.request_cached, episodes.request_hash)

    private fun toRepo(episode: EpisodeResponse): EpisodeRepo =
        EpisodeRepo(episode.aired, episode.episode_id, episode.filler, episode.forum_url, episode.recap, episode.title, episode.title_japanese, episode.title_romanji, episode.video_url)

    override fun toRepo(forum: ForumResponse): ForumRepo =
        ForumRepo(forum.request_cache_expiry, forum.request_cached, forum.request_hash, forum.topics.map { toRepo(it) })

    private fun toRepo(topic: TopicResponse): TopicRepo =
        TopicRepo(topic.author_name, topic.author_url, topic.date_posted, toRepo(topic.last_post), topic.replies, topic.title, topic.topic_id, topic.url)

    private fun toRepo(lastPost: LastPostResponse): LastPostRepo =
        LastPostRepo(lastPost.author_name, lastPost.author_url, lastPost.date_posted, lastPost.url)

    override fun toRepo(moreInfo: MoreInfoResponse): MoreInfoRepo =
        MoreInfoRepo(moreInfo.moreinfo, moreInfo.request_cache_expiry, moreInfo.request_cached, moreInfo.request_hash)

    override fun toRepo(news: NewsResponse): NewsRepo =
        NewsRepo(news.articles.map { toRepo(it) }, news.request_cache_expiry, news.request_cached, news.request_hash)

    private fun toRepo(article: ArticleResponse): ArticleRepo =
        ArticleRepo(article.author_name, article.author_url, article.comments, article.date, article.forum_url, article.image_url, article.intro, article.title, article.url)

    override fun toRepo(pictures: PicturesResponse): PicturesRepo =
        PicturesRepo(pictures.pictures.map { toRepo(it) }, pictures.request_cache_expiry, pictures.request_cached, pictures.request_hash)

    private fun toRepo(picture: PictureResponse): PictureRepo =
        PictureRepo(picture.large, picture.small)

    override fun toRepo(recommendations: RecommendationsResponse): RecommendationsRepo =
        RecommendationsRepo(recommendations.recommendations.map { toRepo(it) }, recommendations.request_cache_expiry, recommendations.request_cached, recommendations.request_hash)

    private fun toRepo(recommendation: RecommendationResponse): RecommendationRepo =
        RecommendationRepo(recommendation.image_url, recommendation.mal_id, recommendation.recommendation_count, recommendation.recommendation_url, recommendation.title, recommendation.url)

    override fun toRepo(reviews: ReviewsResponse): ReviewsRepo =
        ReviewsRepo(reviews.request_cache_expiry, reviews.request_cached, reviews.request_hash, reviews.reviews.map { toRepo(it) })

    private fun toRepo(review: ReviewResponse): ReviewRepo =
        ReviewRepo(review.content, review.date, review.helpful_count, review.mal_id, toRepo(review.reviewer), review.type, review.url)

    private fun toRepo(reviewer: ReviewerResponse): ReviewerRepo =
        ReviewerRepo(reviewer.episodes_seen, reviewer.image_url, toRepo(reviewer.reviewScore), reviewer.url, reviewer.username)

    private fun toRepo(score: ReviewScoreResponse): ReviewScoreRepo =
        ReviewScoreRepo(score.animation, score.character, score.enjoyment, score.overall, score.sound, score.story)

    override fun toRepo(stats: StatsResponse): StatsRepo =
        StatsRepo(stats.completed, stats.dropped, stats.on_hold, stats.plan_to_watch, stats.request_cache_expiry, stats.request_cached, stats.request_hash, toRepo(stats.scores), stats.total, stats.watching)

    override fun toRepo(videos: VideosResponse): VideosRepo =
        VideosRepo(videos.episodes, videos.promo.map { toRepo(it) }, videos.request_cache_expiry, videos.request_cached, videos.request_hash)

    private fun toRepo(promo: PromoResponse): PromoRepo =
        PromoRepo(promo.image_url, promo.title, promo.video_url)
}