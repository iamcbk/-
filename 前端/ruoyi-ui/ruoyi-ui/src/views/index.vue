<template>
  <div class="dashboard-container">
    <el-loading :loading="loading" background="rgba(255, 255, 255, 0.9)">
      <div class="dashboard-welcome">
        <el-card class="welcome-card" :body-style="{ padding: '0px' }">
          <div class="welcome-header">
            <div class="welcome-left">
              <div class="logo-area">
                <i class="el-icon-shopping-cart-2"></i>
                <span class="logo-text">云萃商城</span>
              </div>
              <div class="greeting-area">
                <h1 class="greeting">{{ getGreeting() }}，<span class="user-role">管理员</span></h1>
                <p class="date-info">
                  <i class="el-icon-sunny"></i>
                  今天是{{ currentDate }}，祝您工作愉快！
                </p>
              </div>
            </div>
            <div class="welcome-right">
              <img src="../assets/logo/logo.png" alt="logo" class="vue-logo">
            </div>
          </div>
        </el-card>
      </div>
      <div class="dashboard-stats">
        <el-row :gutter="24">
          <el-col :span="12">
            <el-card class="stats-card total-sales" shadow="hover">
              <div class="stats-header">
                <div class="stats-title">
                  <i class="el-icon-money"></i>
                  <span>总销售额</span>
                </div>
                <el-tag size="small" effect="dark" type="primary">今日</el-tag>
              </div>
              <div class="stats-content">
                <div class="stats-main">
                  <div class="stats-number">¥{{ totalSales.toLocaleString() }}</div>
                </div>
              </div>
              <div class="stats-footer">
                <span>统计时间：{{ currentDate }}</span>
              </div>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card class="stats-card total-orders" shadow="hover">
              <div class="stats-header">
                <div class="stats-title">
                  <i class="el-icon-shopping-cart-full"></i>
                  <span>成交订单数</span>
                </div>
                <el-tag size="small" effect="dark" type="success">今日</el-tag>
              </div>
              <div class="stats-content">
                <div class="stats-main">
                  <div class="stats-number">{{ totalOrders.toLocaleString() }}</div>
                </div>
              </div>
              <div class="stats-footer">
                <span>统计时间：{{ currentDate }}</span>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
      <div class="dashboard-champions">
        <el-card class="champion-card" shadow="hover">
          <div class="card-header">
            <div class="header-title">
              <i class="el-icon-trophy gold"></i>
              <span>销售冠军</span>
            </div>
            <el-tag size="small" effect="dark" type="warning">TOP {{ topSellers.length }}</el-tag>
          </div>
          <div class="champions-list">
            <div class="champion-item" v-for="(seller, index) in topSellers" :key="seller.id">
              <div class="champion-info">
                <div class="champion-medal">
                  <div class="medal-circle">
                    <i class="el-icon-trophy"></i>
                    <span class="rank-text">NO.{{ index + 1 }}</span>
                  </div>
                </div>
                <el-avatar
                  :size="64"
                  :src="seller.avatar"
                  @error="() => true"
                  :default-src="defaultAvatar"
                  class="champion-avatar">
                </el-avatar>
                <div class="champion-name">{{ seller.name }}</div>
              </div>
              <div class="champion-details">
                <div class="champion-stats">
                  <div class="stat-item sales">
                    <i class="el-icon-money"></i>
                    <div class="stat-info">
                      <div class="label">销售额</div>
                      <div class="value">¥{{ seller.sales.toLocaleString() }}</div>
                    </div>
                  </div>
                  <div class="stat-item orders">
                    <i class="el-icon-shopping-cart-full"></i>
                    <div class="stat-info">
                      <div class="label">订单数</div>
                      <div class="value">{{ seller.orders }}</div>
                    </div>
                  </div>
                </div>
                <div class="champion-achievement">
                  <i class="el-icon-medal-1"></i>
                  <span>超越了 100% 的同事</span>
                </div>
              </div>
            </div>
          </div>
        </el-card>
      </div>
      <div class="dashboard-chart">
        <el-card class="chart-card">
          <div class="card-header">
            <div class="chart-title">业绩排行</div>
            <el-tag size="small" type="info">全部</el-tag>
          </div>
          <div class="chart-container" ref="salesChart" style="height: 400px;"></div>
        </el-card>
      </div>
    </el-loading>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import { getDashboardStats, getTopSellers, getSalesRanking } from '@/api/dashboard'

export default {
  name: 'Dashboard',
  data() {
    return {
      currentDate: new Date().toLocaleDateString('zh-CN', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
      }),
      loading: false,
      totalSales: 0,
      totalOrders: 0,
      topSellers: [],
      sellers: [],
      chartInstance: null,
      defaultAvatar: require('@/assets/images/profile.jpg')
    }
  },

  created() {
    this.fetchDashboardData()
  },

  mounted() {
    this.initChart()
    window.addEventListener('resize', this.handleResize)
  },

  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize)
    if (this.chartInstance) {
      this.chartInstance.dispose()
    }
  },

  methods: {
    getGreeting() {
      const hour = new Date().getHours()
      if (hour < 12) return '早上好'
      if (hour < 18) return '中午好'
      return '晚上好'
    },

    async fetchDashboardData() {
      this.loading = true
      try {
        // 获取总体统计数据
        const statsRes = await getDashboardStats()
        if (statsRes.code === 200) {
          const { totalSales, totalOrders } = statsRes.data
          this.totalSales = totalSales
          this.totalOrders = totalOrders
        }

        // 获取销售冠军数据
        const topSellersRes = await getTopSellers()
        console.log('Top sellers response:', topSellersRes) // 调试日志
        if (topSellersRes.code === 200) {
          this.topSellers = topSellersRes.data
          console.log('Processed top sellers:', this.topSellers) // 调试日志
        }

        // 获取销售排行数据
        const rankingRes = await getSalesRanking()
        console.log('rankingRes:', rankingRes) // 调试日志

        if (rankingRes.code === 200) {
          this.sellers = rankingRes.data
          console.log('this.sellers:', this.sellers) // 调试日志
          this.updateChart()
        }
      } catch (error) {
        console.error('获取仪表盘数据失败:', error)
        this.$message.error('获取数据失败，请稍后重试')
      } finally {
        this.loading = false
      }
    },

    updateChart() {
      if (this.chartInstance && this.sellers && this.sellers.length > 0) {
        // 计算订单数的最大值和合适的刻度
        const maxOrders = Math.max(...this.sellers.map(seller => seller.orders))
        // 根据最大订单数动态计算合适的刻度间隔
        let orderInterval
        if (maxOrders <= 10) {
          orderInterval = 2
        } else if (maxOrders <= 50) {
          orderInterval = 10
        } else if (maxOrders <= 100) {
          orderInterval = 20
        } else {
          orderInterval = Math.ceil(maxOrders / 10)  // 将Y轴分成约10个刻度
        }
        const maxOrderAxis = Math.ceil(maxOrders / orderInterval) * orderInterval

        // 计算销售额的最大值和合适的刻度
        const maxSales = Math.max(...this.sellers.map(seller => seller.sales))
        const salesInterval = Math.ceil(maxSales / 5)  // 将Y轴分成约5个刻度
        const maxSalesAxis = Math.ceil(maxSales / salesInterval) * salesInterval

        const option = {
          backgroundColor: '#ffffff',
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          legend: {
            data: ['销售额', '订单数'],
            top: 20
          },
          grid: {
            left: '3%',
            right: '8%',  // 增加右侧空间以容纳第二个Y轴
            bottom: '3%',
            containLabel: true
          },
          xAxis: [{
            type: 'category',
            data: this.sellers.map(seller => seller.name),
            axisLabel: {
              interval: 0,
              color: '#666',
              fontSize: 13
            }
          }],
          yAxis: [
            {
              type: 'value',
              name: '销售额',
              min: 0,
              max: maxSalesAxis,
              interval: salesInterval,
              axisLabel: {
                formatter: '{value}',
                color: '#5e7ce0'
              },
              axisLine: {
                show: true,
                lineStyle: {
                  color: '#5e7ce0'
                }
              },
              splitLine: {
                show: true,
                lineStyle: {
                  type: 'dashed',
                  color: '#eee'
                }
              }
            },
            {
              type: 'value',
              name: '订单数',
              min: 0,
              max: maxOrderAxis,
              interval: orderInterval,
              position: 'right',
              axisLabel: {
                formatter: '{value}',
                color: '#36c6a0'
              },
              axisLine: {
                show: true,
                lineStyle: {
                  color: '#36c6a0'
                }
              },
              splitLine: {
                show: false
              }
            }
          ],
          series: [
            {
              name: '销售额',
              type: 'bar',
              data: this.sellers.map(seller => seller.sales),
              barWidth: '20%',
              itemStyle: {
                color: {
                  type: 'linear',
                  x: 0,
                  y: 0,
                  x2: 0,
                  y2: 1,
                  colorStops: [{
                    offset: 0,
                    color: '#5e7ce0'
                  }, {
                    offset: 1,
                    color: 'rgba(94,124,224,0.05)'
                  }]
                },
                borderRadius: [8, 8, 0, 0]
              }
            },
            {
              name: '订单数',
              type: 'bar',
              yAxisIndex: 1,
              data: this.sellers.map(seller => seller.orders),
              barWidth: '20%',
              itemStyle: {
                color: {
                  type: 'linear',
                  x: 0,
                  y: 0,
                  x2: 0,
                  y2: 1,
                  colorStops: [{
                    offset: 0,
                    color: '#36c6a0'
                  }, {
                    offset: 1,
                    color: 'rgba(54,198,160,0.05)'
                  }]
                },
                borderRadius: [8, 8, 0, 0]
              }
            }
          ]
        }

        this.chartInstance.setOption(option)
      }
    },

    handleResize() {
      if (this.chartInstance) {
        this.chartInstance.resize()
      }
    },

    initTrendCharts() {
      // 销售额趋势图
      const salesChart = echarts.init(this.$refs.salesTrendChart)
      const salesOption = {
        grid: {
          top: 0,
          right: 0,
          bottom: 0,
          left: 0
        },
        xAxis: {
          type: 'category',
          show: false
        },
        yAxis: {
          type: 'value',
          show: false
        },
        series: [{
          data: [150, 230, 224, 218, 135, 147, 260],
          type: 'line',
          smooth: true,
          symbol: 'none',
          lineStyle: {
            color: '#409EFF',
            width: 3
          },
          areaStyle: {
            color: {
              type: 'linear',
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [{
                offset: 0,
                color: 'rgba(64,158,255,0.2)'
              }, {
                offset: 1,
                color: 'rgba(64,158,255,0)'
              }]
            }
          }
        }]
      }
      salesChart.setOption(salesOption)

      // 订单数趋势图
      const ordersChart = echarts.init(this.$refs.ordersTrendChart)
      const ordersOption = {
        grid: {
          top: 0,
          right: 0,
          bottom: 0,
          left: 0
        },
        xAxis: {
          type: 'category',
          show: false
        },
        yAxis: {
          type: 'value',
          show: false
        },
        series: [{
          data: [120, 182, 191, 234, 190, 330, 310],
          type: 'line',
          smooth: true,
          symbol: 'none',
          lineStyle: {
            color: '#67C23A',
            width: 3
          },
          areaStyle: {
            color: {
              type: 'linear',
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [{
                offset: 0,
                color: 'rgba(103,194,58,0.2)'
              }, {
                offset: 1,
                color: 'rgba(103,194,58,0)'
              }]
            }
          }
        }]
      }
      ordersChart.setOption(ordersOption)
    },

    initChart() {
      // 确保有容器元素
      if (this.$refs.salesChart) {
        // 如果已经有实例先销毁
        if (this.chartInstance) {
          this.chartInstance.dispose()
        }
        // 初始化图表
        this.chartInstance = echarts.init(this.$refs.salesChart)
        console.log('图表初始化完成:', this.chartInstance) // 调试日志
      } else {
        console.warn('图表容器不存在')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-container {
  padding: 24px;
  background-color: #f6f8fb;

  .dashboard-welcome {
    .welcome-card {
      background: #f8fafc;
      border: none;
      border-radius: 12px;
      overflow: hidden;

      .welcome-header {
        padding: 32px 40px;
        display: flex;
        justify-content: space-between;
        align-items: center;

        .welcome-left {
          flex: 1;

          .logo-area {
            display: flex;
            align-items: center;
            margin-bottom: 20px;

            .el-icon-shopping-cart-2 {
              font-size: 28px;
              color: #409EFF;
              background: rgba(64, 158, 255, 0.1);
              padding: 12px;
              border-radius: 10px;
            }

            .logo-text {
              margin-left: 15px;
              font-size: 22px;
              color: #409EFF;
              font-weight: 500;
            }
          }

          .greeting-area {
            .greeting {
              font-size: 24px;
              color: #333;
              font-weight: 500;
              margin: 0;

              .user-role {
                color: #409EFF;
              }
            }

            .date-info {
              display: flex;
              align-items: center;
              margin: 12px 0 0;
              color: #666;
              font-size: 16px;

              .el-icon-sunny {
                color: #F0B72F;
                margin-right: 10px;
                font-size: 20px;
              }
            }
          }
        }

        .welcome-right {
          margin-left: 40px;

          .vue-logo {
            width: 180px;
            height: auto;
            opacity: 0.8;
          }
        }
      }
    }
  }

  .dashboard-stats {
    margin-top: 24px;

    .stats-card {
      height: 100%;
      border-radius: 16px;
      border: none;
      transition: all 0.3s ease;

      .stats-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 24px;

        .stats-title {
          display: flex;
          align-items: center;
          font-size: 16px;
          font-weight: 600;
          color: #1f2f3d;

          i {
            font-size: 22px;
            margin-right: 8px;
          }
        }
      }

      .stats-content {
        .stats-main {
          .stats-number {
            font-size: 36px;
            font-weight: bold;
            color: #303133;
            font-family: DIN;
            line-height: 1.2;
            margin-bottom: 24px;
          }
        }
      }

      .stats-footer {
        margin-top: 24px;
        padding-top: 16px;
        border-top: 1px solid #f0f2f5;
        color: #909399;
        font-size: 13px;
      }

      &.total-sales {
        background: linear-gradient(135deg, #ffffff 0%, #f8faff 100%);
        .stats-title i {
          color: #409EFF;
        }
      }

      &.total-orders {
        background: linear-gradient(135deg, #ffffff 0%, #f8fff9 100%);
        .stats-title i {
          color: #67C23A;
        }
      }
    }
  }

  .dashboard-champions {
    margin-top: 24px;

    .champion-card {
      border-radius: 12px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);
      max-width: 800px;
      margin: 0 auto;

      .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 24px;
        padding: 8px 0;
        text-align: center;

        .header-title {
          display: flex;
          align-items: center;
          font-size: 18px;
          font-weight: 600;
          color: #303133;

          i {
            color: #ffd700;
            margin-right: 12px;
            font-size: 24px;
            text-shadow: 0 2px 4px rgba(255, 215, 0, 0.3);
          }
        }
      }

      .champions-list {
        .champion-item {
          padding: 24px;
          background: linear-gradient(to right, #f8f9fa, #ffffff);
          border-radius: 12px;
          margin: 0 auto 20px;
          max-width: 600px;
          transition: all 0.3s ease;
          border: 1px solid rgba(0, 0, 0, 0.05);

          &:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.08);
          }

          &:last-child {
            margin-bottom: 0;
          }

          .champion-info {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 20px;

            .champion-medal {
              margin-right: 20px;

              .medal-circle {
                background: linear-gradient(135deg, #ffd700 0%, #ff9500 100%);
                color: white;
                padding: 12px;
                border-radius: 50%;
                display: flex;
                flex-direction: column;
                align-items: center;
                box-shadow: 0 4px 12px rgba(255, 215, 0, 0.3);

                i {
                  font-size: 20px;
                  margin-bottom: 4px;
                }

                .rank-text {
                  font-size: 12px;
                  font-weight: 600;
                }
              }
            }

            .champion-avatar {
              margin-right: 20px;
              border: 3px solid #fff;
              box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            }

            .champion-name {
              font-size: 18px;
              font-weight: 600;
              color: #303133;
              text-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
            }
          }

          .champion-details {
            .champion-stats {
              display: flex;
              justify-content: center;
              gap: 32px;
              margin-bottom: 20px;

              .stat-item {
                display: flex;
                align-items: center;
                padding: 12px 16px;
                background: #ffffff;
                border-radius: 12px;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);

                i {
                  font-size: 24px;
                  margin-right: 12px;
                  padding: 10px;
                  border-radius: 10px;
                }

                &.sales i {
                  color: #409EFF;
                  background: rgba(64, 158, 255, 0.1);
                }

                &.orders i {
                  color: #67C23A;
                  background: rgba(103, 194, 58, 0.1);
                }

                .stat-info {
                  .label {
                    font-size: 14px;
                    color: #909399;
                    margin-bottom: 4px;
                  }

                  .value {
                    font-size: 18px;
                    font-weight: 600;
                    color: #303133;
                  }
                }
              }
            }

            .champion-achievement {
              display: flex;
              align-items: center;
              justify-content: center;
              margin: 0 auto;
              padding: 8px 16px;
              background: linear-gradient(135deg, #fff3e0, #ffe0b2);
              border-radius: 20px;
              color: #f7ba2a;
              font-size: 14px;
              font-weight: 500;
              box-shadow: 0 2px 8px rgba(247, 186, 42, 0.2);
              width: fit-content;

              i {
                margin-right: 8px;
                font-size: 16px;
              }
            }
          }
        }
      }
    }
  }

  .dashboard-chart {
    margin-top: 24px;

    .chart-card {
      border-radius: 16px;
      border: none;

      .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;

        .chart-title {
          font-size: 16px;
          font-weight: 600;
          color: #1f2f3d;
        }
      }

      .chart-container {
        width: 100%;
        height: 400px;
      }
    }
  }
}
</style>
