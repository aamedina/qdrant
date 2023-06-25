# net.wikipunk/qdrant
Clojure API client and RDF vocabulary for Qdrant Cloud

## Configuration
### Option 1: Configure with environment variables 
Set these to configure the client without using component.
#### QDRANT_API_KEY 
The Qdrant Cloud API key for the cluster you want to connect to.
#### QDRANT_URL 
The Qdrant Cloud cluster URL.
#### QDRANT_PORT
The Qdrant Cloud cluster's port. Defaults to 6333 when unset.

### Option 2: Configure using a component
`net.wikipunk.qdrant/map->Client` can be provided :api-key and
:base-url (including the port) to override the environment variables
and included in your system. For an example of a component system, see
the [dev system](dev/system.edn).

This option is required if you want to use search/recommend functions
with metaobjects and OpenAI embeddings.

## :rdfs/seeAlso 
* https://qdrant.github.io/qdrant/redoc/index.html
* https://github.com/wkok/openai-clojure (Inspiration for API client generation)

## :dev

``` shell
clojure -A:dev
```

``` clojure
(reset)
```

## License

Copyright (c) 2023 Adrian Medina

Permission to use, copy, modify, and/or distribute this software for
any purpose with or without fee is hereby granted, provided that the
above copyright notice and this permission notice appear in all
copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL
WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE
AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL
DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
PERFORMANCE OF THIS SOFTWARE.
