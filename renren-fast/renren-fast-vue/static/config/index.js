/**
 * 开发环境
 */
(function () {
  //
  window.SITE_CONFIG = {}

  // TODO 后端地址
  // api接口请求地址
  window.SITE_CONFIG['baseUrl'] = 'http://localhost:1000/renren-fast'

  // cdn地址 = 域名 + 版本号
  window.SITE_CONFIG['domain'] = './' // 域名
  window.SITE_CONFIG['version'] = ''   // 版本号(年月日时分)
  window.SITE_CONFIG['cdnUrl'] = window.SITE_CONFIG.domain + window.SITE_CONFIG.version
})()
