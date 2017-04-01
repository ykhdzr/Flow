[![License](https://img.shields.io/github/license/pluscubed/recycler-fast-scroll.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Flow-green.svg?style=true)](https://android-arsenal.com/details/1/5451)


Flow
=======
Abilities is to Start an Activity : 
1. from Activity
2. from Fragment
3. with Intent Filter
4. with Bundle
5. with Intent Flag
6. for result

Usage
=======
``` java
// From Activity/Fragment
Flow
  .from(this)
  .to(DestinationActivity.class)        

// From Activity/Fragment to Intent Filter
Flow
  .from(this)
  .to(DestinationActivity.INTENT_FILTER)        

// From Activity/Fragment with Bundle
Flow
  .from(this)
  .with(provideBundle())
  .to(DestinationActivity.class)        

// From Activity/Fragment with Flag
Flow
  .from(this)
  .flag(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
  .to(DestinationActivity.class)   
  
// From Activity/Fragment forResult
Flow
  .from(this)
  .forResult(DestinationActivity.REQUEST_CODE)
  .to(DestinationActivity.class)   

```


Installation
=======
To include in your gradle app:

	dependencies {
	  compile 'com.ykhdzr:flow::1.0.0'
	}

License
=======

Copyright 2017 Yoko Ahadazaro

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
