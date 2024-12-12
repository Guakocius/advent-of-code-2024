# frozen_string_literal: true

# Day12 class is designed to process a map of garden plots and identify distinct regions of plants.
# It parses the input map, performs a depth-first search (DFS) to explore connected plots of the same plant type,
# and counts the number of regions of each plant type in the map.
#
# Methods include:
# - `parse_map(file)`: Reads the map from a file and converts it into a 2D array.
# - `dfs(map, visited, x_pos, y_pos, plant_type)`: Performs a depth-first search from a given position.
# - `find_plots_of_land(map)`: Finds and counts the regions of connected plant plots on the map.
# - Helper methods for checking neighboring plots (up, down, left, right).
#
# The class maintains the state of the map, visited positions, and tracks plant regions as it processes the map.
class Day12
  def initialize(map)
    @map = parse_map('inputs12.txt')
    @plots = find_plots_of_land(map)

    @visited = Array.new(map.length) { Array.new(map[0].length, false) }
    @plant_type = map[x_pos][y_pos]
  end

  def parse_map(file)
    File.readlines(file).map { |line| line.chomp.chars }
  end

  def dfs(map, visited, x_pos, y_pos, plant_type)
    return if visited[x_pos][y_pos]

    visited[x_pos][y_pos] = true

    check_neighbors(map, visited, x_pos, y_pos, plant_type)
  end

  def check_neighbors(map, visited, x_pos, y_pos, plant_type)
    check_up(map, visited, x_pos, y_pos, plant_type)
    check_down(map, visited, x_pos, y_pos, plant_type)
    check_left(map, visited, x_pos, y_pos, plant_type)
    check_right(map, visited, x_pos, y_pos, plant_type)
  end

  def check_up(map, visited, x_pos, y_pos, plant_type)
    return if x_pos.zero?

    dfs(map, visited, x_pos - 1, y_pos, plant_type) if map[x_pos - 1][y_pos] == plant_type && !visited[x_pos - 1][y_pos]
  end

  def check_down(map, visited, x_pos, y_pos, plant_type)
    return if x_pos == map.length - 1

    dfs(map, visited, x_pos + 1, y_pos, plant_type) if map[x_pos + 1][y_pos] == plant_type && !visited[x_pos + 1][y_pos]
  end

  def check_left(map, visited, x_pos, y_pos, plant_type)
    return if y_pos.zero?

    dfs(map, visited, x_pos, y_pos - 1, plant_type) if map[x_pos][y_pos - 1] == plant_type && !visited[x_pos][y_pos - 1]
  end

  def check_right(map, visited, x_pos, y_pos, plant_type)
    return if y_pos == map[0].length - 1

    dfs(map, visited, x_pos, y_pos + 1, plant_type) if map[x_pos][y_pos + 1] == plant_type && !visited[x_pos][y_pos + 1]
  end

  def find_plots_of_land(map)
    plots = Hash.new(0)

    map.each_with_index do |row, x_pos|
      row.each_with_index do |_, y_pos|
        next if @visited[x_pos][y_pos]

        plots[plant_type] += 1
        total_plots += 1
        dfs(map, visited, x_pos, y_pos, plant_type)
      end
    end
    puts "Total number of plots: #{total_plots}"
    plots
  end
  map = parse_map('inputs12.txt')
end
