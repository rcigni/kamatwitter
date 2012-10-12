class UrlMappings {

	static mappings = {

        "/stream/$username"(controller: 'timeline', action: 'authorTweets')

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
